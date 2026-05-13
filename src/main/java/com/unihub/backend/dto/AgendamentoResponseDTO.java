package com.unihub.backend.dto;

import com.unihub.backend.enums.StatusAgendamento;
import com.unihub.backend.enums.TipoAgendamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoResponseDTO {
    private Long idAgendamento;
    private Long idUsuario;
    private String nomeUsuario;
    private String local; // Nome da sala ou mesa
    private LocalDate data;
    private LocalTime horario;
    private String justificativa;
    private StatusAgendamento status;
    private TipoAgendamento tipo;
}
