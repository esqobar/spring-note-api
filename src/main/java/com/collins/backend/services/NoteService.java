package com.collins.backend.services;

import com.collins.backend.dtos.NoteDto;
import com.collins.backend.entities.Note;
import com.collins.backend.entities.User;
import com.collins.backend.exceptions.*;
import com.collins.backend.repositories.NoteRepository;
import com.collins.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private ModelMapper modelMapper;
    public NoteDto createPost(NoteDto noteDto) {
        try {
            //check if post already exists
            String email = authService.getCurrentUser();
            Optional<Note> optionalNote = noteRepository.findByTitle(noteDto.getTitle());
            if (optionalNote.isPresent()) {
                throw new TitleAlreadyExistsException("This Title Already Exists!");
            }

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with that Email Address: " + email));
            Note newNote = modelMapper.map(noteDto, Note.class);
            newNote.setUser(user);
            Note savedNote = noteRepository.save(newNote);
            NoteDto noteResponse =  modelMapper.map(savedNote, NoteDto.class);
            return noteResponse;
        } catch (DataAccessException e) {
            throw new NoteServiceException("Error while creating note", e);
        }
    }

    public List<NoteDto> getUserNotes(){
        String email = authService.getCurrentUser();
        userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with that Email: " + email));

        List<Note> userNotes = noteRepository.findByUserEmail(email);
        return userNotes.stream()
                .map((note) -> modelMapper.map(note, NoteDto.class))
                .collect(Collectors.toList());
    }

    public List<NoteDto> getAllNotes(){
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map((note) -> modelMapper.map(note, NoteDto.class)).collect(Collectors.toList());
    }


    public void deleteNoteByIdAndCurrentUser(Long id){
        try {
            String email = authService.getCurrentUser();
            Note noteToDelete = noteRepository.findById(id)
                    .orElseThrow(() ->
                            new NoteNotFoundException("Note with id not found: " +id));
            if(!noteToDelete.getUser().getEmail().equals(email)){
                throw new NoteOwnershipException("Forbidden: You can only delete your own notes");
            }
            noteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new NoteNotFoundException("Note with id not found: " +id);
        } catch (DataAccessException e){
            throw new NoteServiceException("Error while deleting note", e);
        }

    }

    public NoteDto updateNoteByIdAndCurrentUser(NoteDto noteDto, Long id){
        try {
            String email = authService.getCurrentUser();
            Note existingNote = noteRepository.findById(id)
                    .orElseThrow(() ->
                            new NoteNotFoundException("Note with id not found: " +id));
            if(!existingNote.getUser().getEmail().equals(email)){
                throw new NoteOwnershipException("Forbidden: You can only update your own notes");
            }
            existingNote.setTitle(noteDto.getTitle());
            existingNote.setBody(noteDto.getBody());
            Note updatedNote = noteRepository.save(existingNote);
            //save
            return modelMapper.map(updatedNote, NoteDto.class);
        } catch (EmptyResultDataAccessException e){
            throw new NoteNotFoundException("Note with id not found: " + id);
        } catch (DataAccessException e){
            throw new NoteServiceException("Error while deleting note", e);
        }

    }


    public void deleteAllNoteByUsers(User user){
        noteRepository.deleteByUser(user);
    }

    public long countNotes(){
        return noteRepository.count();
    }

    public long countNotesForUser(String email){
        return noteRepository.countByEmail(email);
    }

}
