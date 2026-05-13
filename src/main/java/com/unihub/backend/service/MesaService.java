package com.unihub.backend.service;

import com.unihub.backend.dto.MesaRequestDTO;
import com.unihub.backend.dto.MesaResponseDTO;

import java.util.List;

public interface MesaService {
    MesaResponseDTO criar(MesaRequestDTO request);
    List<MesaResponseDTO> listarTodas();
    MesaResponseDTO buscarPorId(Long id);
    MesaResponseDTO atualizar(Long id, MesaRequestDTO request);
    void deletar(Long id);
}
