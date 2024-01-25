package com.collins.backend.resources;

import com.collins.backend.dtos.NoteDto;
import com.collins.backend.dtos.RegisterDto;
import com.collins.backend.dtos.UserReponseDto;
import com.collins.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteNote(){
        userService.deleteCurrentUser();
        return new ResponseEntity<>("User and Notes successfully deleted", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserAndNotes(@PathVariable("id") Long id){
        userService.deleteUserAndAssociatedNotes(id);
        return new ResponseEntity<>("User and Notes successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/details/{email}")
    public ResponseEntity<RegisterDto> userDetails(@PathVariable("email") String email){
        RegisterDto userDetails = userService.getUserDetails(email);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserReponseDto>> allUsers(){
        List<UserReponseDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //counts
    @GetMapping("/count")
    public ResponseEntity<Long> countUsers(){
        Long numberOfNotes = userService.countUsers();
        return new ResponseEntity<>(numberOfNotes, HttpStatus.OK);
    }
}
