package com.unihub.backend.dto;

import com.unihub.backend.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O RA é obrigatório")
    private String ra;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @NotNull(message = "O tipo de usuário é obrigatório")
    private TipoUsuario tipoUsuario;
}
