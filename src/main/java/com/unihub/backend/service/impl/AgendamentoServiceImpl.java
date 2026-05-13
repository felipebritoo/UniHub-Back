package com.unihub.backend.service.impl;

import com.unihub.backend.dto.AgendamentoRequestDTO;
import com.unihub.backend.dto.AgendamentoResponseDTO;
import com.unihub.backend.enums.StatusAgendamento;
import com.unihub.backend.enums.TipoAgendamento;
import com.unihub.backend.exception.BusinessException;
import com.unihub.backend.model.Agendamento;
import com.unihub.backend.model.Mesa;
import com.unihub.backend.model.Sala;
import com.unihub.backend.model.Usuario;
import com.unihub.backend.repository.AgendamentoRepository;
import com.unihub.backend.repository.MesaRepository;
import com.unihub.backend.repository.SalaRepository;
import com.unihub.backend.repository.UsuarioRepository;
import com.unihub.backend.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SalaRepository salaRepository;
    private final MesaRepository mesaRepository;

    @Override
    @Transactional
    public AgendamentoResponseDTO criar(AgendamentoRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        Agendamento agendamento = Agendamento.builder()
                .usuario(usuario)
                .data(request.getData())
                .horario(request.getHorario())
                .justificativa(request.getJustificativa())
                .status(StatusAgendamento.PENDENTE)
                .tipo(request.getTipo())
                .build();

        if (request.getTipo() == TipoAgendamento.SALA) {
            if (request.getIdSala() == null) throw new BusinessException("ID da sala é obrigatório para este tipo");
            Sala sala = salaRepository.findById(request.getIdSala())
                    .orElseThrow(() -> new BusinessException("Sala não encontrada"));
            agendamento.setSala(sala);
        } else {
            if (request.getIdMesa() == null) throw new BusinessException("ID da mesa é obrigatório para este tipo");
            Mesa mesa = mesaRepository.findById(request.getIdMesa())
                    .orElseThrow(() -> new BusinessException("Mesa não encontrada"));
            agendamento.setMesa(mesa);
        }

        Agendamento salvo = agendamentoRepository.save(agendamento);
        return mapToResponse(salvo);
    }

    @Override
    public List<AgendamentoResponseDTO> listarPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        return agendamentoRepository.findByUsuario(usuario).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelar(Long idAgendamento) {
        Agendamento agendamento = agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new BusinessException("Agendamento não encontrado"));
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        agendamentoRepository.save(agendamento);
    }

    @Override
    public List<AgendamentoResponseDTO> listarTodos() {
        return agendamentoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AgendamentoResponseDTO mapToResponse(Agendamento a) {
        String local = (a.getTipo() == TipoAgendamento.SALA) ? a.getSala().getNome() : a.getMesa().getNome();
        return AgendamentoResponseDTO.builder()
                .idAgendamento(a.getIdAgendamento())
                .idUsuario(a.getUsuario().getIdUsuario())
                .nomeUsuario(a.getUsuario().getNome())
                .local(local)
                .data(a.getData())
                .horario(a.getHorario())
                .justificativa(a.getJustificativa())
                .status(a.getStatus())
                .tipo(a.getTipo())
                .build();
    }
}
