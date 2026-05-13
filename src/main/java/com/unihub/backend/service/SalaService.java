package com.unihub.backend.service;

import com.unihub.backend.dto.SalaRequestDTO;
import com.unihub.backend.dto.SalaResponseDTO;
import java.util.List;

public interface SalaService {
    SalaResponseDTO criar(SalaRequestDTO request);
    List<SalaResponseDTO> listarTodas();
    SalaResponseDTO buscarPorId(Long id);
    List<SalaResponseDTO> buscarPorPredio(String predio);
    SalaResponseDTO atualizar(Long id, SalaRequestDTO request);
    void deletar(Long id);
}
