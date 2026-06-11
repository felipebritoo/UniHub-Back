package com.unihub.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChaveVirtualRequestDTO {
    
    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario;
    
    @NotNull(message = "O ID da sala é obrigatório")
    private Long idSala;
}
