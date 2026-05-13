package com.unihub.backend.service;

import com.unihub.backend.dto.AgendamentoRequestDTO;
import com.unihub.backend.dto.AgendamentoResponseDTO;
import java.util.List;

public interface AgendamentoService {
    AgendamentoResponseDTO criar(AgendamentoRequestDTO request);
    List<AgendamentoResponseDTO> listarPorUsuario(Long idUsuario);
    void cancelar(Long idAgendamento);
    List<AgendamentoResponseDTO> listarTodos();
}
