package com.collins.backend.mappers;

import com.collins.backend.dtos.NoteDto;
import com.collins.backend.dtos.UserReponseDto;
import com.collins.backend.entities.Note;
import com.collins.backend.entities.User;

public class UserResponseMapper {
    public static UserReponseDto mapToUserReponseDto(User user){
        UserReponseDto userReponseDto = new UserReponseDto();
        userReponseDto.setId(user.getId());
        userReponseDto.setName(user.getName());
        userReponseDto.setUsername(user.getUsername());
        userReponseDto.setEmail(user.getEmail());
        return userReponseDto;
    }

    //convert Dto into JPA Entity
    public static User mapToUser(UserReponseDto userReponseDto){
        User user = new User();
        user.setId(userReponseDto.getId());
        user.setName(userReponseDto.getName());
        user.setUsername(userReponseDto.getUsername());
        user.setEmail(userReponseDto.getEmail());
        return user;
    }
}
