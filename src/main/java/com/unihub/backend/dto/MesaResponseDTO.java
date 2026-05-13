package com.unihub.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MesaResponseDTO {
    private Long idMesa;
    private String nome;
    private String lado;
    private Boolean disponivel;
}
