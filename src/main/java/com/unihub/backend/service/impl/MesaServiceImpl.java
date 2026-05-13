package com.unihub.backend.service.impl;

import com.unihub.backend.dto.MesaRequestDTO;
import com.unihub.backend.dto.MesaResponseDTO;
import com.unihub.backend.exception.BusinessException;
import com.unihub.backend.model.Mesa;
import com.unihub.backend.repository.MesaRepository;
import com.unihub.backend.service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MesaServiceImpl implements MesaService {

    private final MesaRepository mesaRepository;

    @Override
    @Transactional
    public MesaResponseDTO criar(MesaRequestDTO request) {
        Mesa mesa = Mesa.builder()
                .nome(request.getNome())
                .lado(request.getLado())
                .disponivel(request.getDisponivel())
                .build();

        return mapToResponse(mesaRepository.save(mesa));
    }

    @Override
    public List<MesaResponseDTO> listarTodas() {
        return mesaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MesaResponseDTO buscarPorId(Long id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Mesa não encontrada"));
        return mapToResponse(mesa);
    }

    @Override
    @Transactional
    public MesaResponseDTO atualizar(Long id, MesaRequestDTO request) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Mesa não encontrada"));

        mesa.setNome(request.getNome());
        mesa.setLado(request.getLado());
        mesa.setDisponivel(request.getDisponivel());

        return mapToResponse(mesaRepository.save(mesa));
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!mesaRepository.existsById(id)) {
            throw new BusinessException("Mesa não encontrada");
        }
        mesaRepository.deleteById(id);
    }

    private MesaResponseDTO mapToResponse(Mesa mesa) {
        return MesaResponseDTO.builder()
                .idMesa(mesa.getIdMesa())
                .nome(mesa.getNome())
                .lado(mesa.getLado())
                .disponivel(mesa.getDisponivel())
                .build();
    }
}
