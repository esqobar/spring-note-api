package com.collins.backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "UserResponseDto Model Information"
)
public class UserReponseDto {
    private Long id;
    @Schema(
            description = "Note Register Name"
    )
    private String name;
    @Schema(
            description = "Note Register Username"
    )
    private String username;
    @Schema(
            description = "Note Register Email"
    )
    private String email;
}
