package com.collins.backend.resources;

import com.collins.backend.dtos.NoteDto;
import com.collins.backend.entities.Note;
import com.collins.backend.services.NoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteDto noteDto){
       NoteDto saveNoteDto = noteService.createPost(noteDto);
        return new ResponseEntity<>(saveNoteDto, HttpStatus.CREATED);
    }

    @GetMapping("/my-notes")
    public ResponseEntity<List<NoteDto>> userNotes(){
        List<NoteDto> userNotes = noteService.getUserNotes();
        return new ResponseEntity<>(userNotes, HttpStatus.OK);
    }

    @GetMapping("/all-notes")
    public ResponseEntity<List<NoteDto>> allNotes(){
        List<NoteDto> allNotes = noteService.getAllNotes();
        return new ResponseEntity<>(allNotes, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCurrentUserNote(@PathVariable("id") Long id){
        noteService.deleteNoteByIdAndCurrentUser(id);
        return new ResponseEntity<>("Note successfully deleted", HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<NoteDto> updateCurrentUserNote(@PathVariable("id") Long id, @RequestBody NoteDto noteDto){
        NoteDto updatedNote = noteService.updateNoteByIdAndCurrentUser(noteDto, id);
        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }

    //counts
    @GetMapping("/count")
    public ResponseEntity<Long> countNotes(){
        Long numberOfNotes = noteService.countNotes();
        return new ResponseEntity<>(numberOfNotes, HttpStatus.OK);
    }

    @GetMapping("/count/{email}")
    public ResponseEntity<Long> countUserNote(@PathVariable("email") String email){
        Long numberOfNotes = noteService.countNotesForUser(email);
        return new ResponseEntity<>(numberOfNotes, HttpStatus.OK);
    }

}
