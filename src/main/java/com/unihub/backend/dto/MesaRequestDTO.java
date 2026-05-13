package com.unihub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MesaRequestDTO {

    @NotBlank(message = "O nome da mesa é obrigatório")
    private String nome;

    @NotBlank(message = "O lado é obrigatório (left/right)")
    private String lado;

    @NotNull(message = "A disponibilidade é obrigatória")
    private Boolean disponivel;
}
