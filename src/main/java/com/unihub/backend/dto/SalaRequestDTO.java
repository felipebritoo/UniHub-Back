package com.unihub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaRequestDTO {

    @NotBlank(message = "O nome da sala é obrigatório")
    private String nome;

    @NotBlank(message = "O prédio é obrigatório")
    private String predio;

    @NotNull(message = "A capacidade é obrigatória")
    @Positive(message = "A capacidade deve ser maior que zero")
    private Integer capacidade;

    private String recursos;
}
