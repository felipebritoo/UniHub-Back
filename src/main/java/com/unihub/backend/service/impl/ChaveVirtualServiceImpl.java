package com.unihub.backend.service.impl;

import com.unihub.backend.dto.ChaveVirtualRequestDTO;
import com.unihub.backend.dto.ChaveVirtualResponseDTO;
import com.unihub.backend.enums.TipoUsuario;
import com.unihub.backend.exception.BusinessException;
import com.unihub.backend.model.Sala;
import com.unihub.backend.model.Usuario;
import com.unihub.backend.repository.SalaRepository;
import com.unihub.backend.repository.UsuarioRepository;
import com.unihub.backend.service.ChaveVirtualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChaveVirtualServiceImpl implements ChaveVirtualService {

    private final UsuarioRepository usuarioRepository;
    private final SalaRepository salaRepository;

    @Override
    @Transactional(readOnly = true)
    public ChaveVirtualResponseDTO destravarSala(ChaveVirtualRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        Sala sala = salaRepository.findById(request.getIdSala())
                .orElseThrow(() -> new BusinessException("Sala não encontrada"));

        if (!TipoUsuario.PROFESSOR.equals(usuario.getTipoUsuario())) {
            throw new BusinessException("Apenas professores podem utilizar a chave virtual.");
        }

        // Simulação de destravamento (MVP Mockado)
        return ChaveVirtualResponseDTO.builder()
                .status("SUCESSO")
                .mensagem("Sala destravada com sucesso.")
                .idSala(sala.getIdSala())
                .build();
    }
}
