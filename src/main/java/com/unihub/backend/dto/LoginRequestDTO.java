package com.unihub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "O RA ou E-mail é obrigatório")
    private String identifier; // RA ou E-mail

    @NotBlank(message = "A senha é obrigatória")
    private String senha;
}
