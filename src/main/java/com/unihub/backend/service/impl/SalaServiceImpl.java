package com.unihub.backend.service.impl;

import com.unihub.backend.dto.SalaRequestDTO;
import com.unihub.backend.dto.SalaResponseDTO;
import com.unihub.backend.exception.BusinessException;
import com.unihub.backend.model.Sala;
import com.unihub.backend.repository.SalaRepository;
import com.unihub.backend.service.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;

    @Override
    @Transactional
    public SalaResponseDTO criar(SalaRequestDTO request) {
        if (salaRepository.findByNome(request.getNome()).isPresent()) {
            throw new BusinessException("Sala com este nome já existe");
        }

        Sala sala = Sala.builder()
                .nome(request.getNome())
                .predio(request.getPredio())
                .capacidade(request.getCapacidade())
                .recursos(request.getRecursos())
                .build();

        return mapToResponse(salaRepository.save(sala));
    }

    @Override
    public List<SalaResponseDTO> listarTodas() {
        return salaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SalaResponseDTO buscarPorId(Long id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Sala não encontrada"));
        return mapToResponse(sala);
    }

    @Override
    public List<SalaResponseDTO> buscarPorPredio(String predio) {
        return salaRepository.findByPredio(predio).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SalaResponseDTO atualizar(Long id, SalaRequestDTO request) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Sala não encontrada"));

        salaRepository.findByNome(request.getNome())
                .ifPresent(s -> {
                    if (!s.getIdSala().equals(id)) {
                        throw new BusinessException("Já existe outra sala com este nome");
                    }
                });

        sala.setNome(request.getNome());
        sala.setPredio(request.getPredio());
        sala.setCapacidade(request.getCapacidade());
        sala.setRecursos(request.getRecursos());

        return mapToResponse(salaRepository.save(sala));
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new BusinessException("Sala não encontrada");
        }
        salaRepository.deleteById(id);
    }

    private SalaResponseDTO mapToResponse(Sala sala) {
        return SalaResponseDTO.builder()
                .idSala(sala.getIdSala())
                .nome(sala.getNome())
                .predio(sala.getPredio())
                .capacidade(sala.getCapacidade())
                .recursos(sala.getRecursos())
                .build();
    }
}
