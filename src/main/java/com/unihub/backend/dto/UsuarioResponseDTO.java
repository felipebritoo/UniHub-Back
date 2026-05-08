package com.unihub.backend.dto;

import com.unihub.backend.enums.TipoUsuario;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String nome;
    private String ra;
    private String email;
    private TipoUsuario tipoUsuario;
}
