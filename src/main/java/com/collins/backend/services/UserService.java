package com.collins.backend.services;

import com.collins.backend.dtos.RegisterDto;
import com.collins.backend.dtos.UserReponseDto;
import com.collins.backend.entities.User;
import com.collins.backend.repositories.NoteRepository;
import com.collins.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private NoteService noteService;
    private ModelMapper modelMapper;

    @Autowired
    private AuthService authService;



    public List<UserReponseDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> modelMapper.map(user, UserReponseDto.class)).collect(Collectors.toList());
    }

    //delete user and associated notes
    @Transactional
    public void deleteUserAndAssociatedNotes(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with that Email Address: " +id));
        noteService.deleteAllNoteByUsers(user);
        userRepository.delete(user);
    }

    //delete a user currently logged in
    @Transactional
    public void deleteCurrentUser(){
        String email = authService.getCurrentUser();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with that Email Address: " +email));
        userRepository.delete(user);
    }

    public RegisterDto getUserDetails(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with that Email Address: " +email));
        return modelMapper.map(user, RegisterDto.class);
    }

    public long countUsers(){
        return userRepository.count();
    }
}
