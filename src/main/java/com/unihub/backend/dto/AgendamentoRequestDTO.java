package com.unihub.backend.dto;

import com.unihub.backend.enums.TipoAgendamento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario;

    private Long idSala;

    private Long idMesa;

    @NotNull(message = "A data é obrigatória")
    private LocalDate data;

    @NotNull(message = "O horário é obrigatório")
    private LocalTime horario;

    private String justificativa;

    @NotNull(message = "O tipo de agendamento é obrigatório")
    private TipoAgendamento tipo;
}
