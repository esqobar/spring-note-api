package com.collins.backend.mappers;

import com.collins.backend.dtos.NoteDto;
import com.collins.backend.entities.Note;

public class NoteMapper {

    public static NoteDto mapToNoteDto(Note note){
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setTitle(note.getTitle());
        noteDto.setBody(note.getBody());
        return noteDto;
    }

    //convert Dto into JPA Entity
    public static Note mapToNote(NoteDto noteDto){
        Note note = new Note();
        note.setId(noteDto.getId());
        note.setTitle(noteDto.getTitle());
        note.setBody(noteDto.getBody());
        return note;
    }
}
