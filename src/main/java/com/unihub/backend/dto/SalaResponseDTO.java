package com.unihub.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaResponseDTO {
    private Long idSala;
    private String nome;
    private String predio;
    private Integer capacidade;
    private String recursos;
}
