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
        description = "LoginDto Model Information"
)
public class LoginDto {
    @Schema(
            description = "Note Login Email"
    )
    private String email;
    @Schema(
            description = "Note Login Password"
    )
    private String password;
}
