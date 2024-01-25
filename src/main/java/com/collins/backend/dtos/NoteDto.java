package com.collins.backend.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "NoteDto Model Information"
)
public class NoteDto {
    private Long id;
    @Schema(
            description = "Note title"
    )
    private String title;
    @Schema(
            description = "Note Body"
    )
    private String body;
}
