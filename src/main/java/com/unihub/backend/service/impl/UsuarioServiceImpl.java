package com.unihub.backend.service.impl;

import com.unihub.backend.dto.UsuarioRequestDTO;
import com.unihub.backend.dto.UsuarioResponseDTO;
import com.unihub.backend.model.Usuario;
import com.unihub.backend.repository.UsuarioRepository;
import com.unihub.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO salvar(UsuarioRequestDTO request) {
        // Validação simples (Poderia lançar exceções customizadas)
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        if (usuarioRepository.findByRa(request.getRa()).isPresent()) {
            throw new RuntimeException("RA já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .ra(request.getRa())
                .email(request.getEmail())
                .senha(request.getSenha()) // TODO: Aplicar BCrypt no futuro
                .tipoUsuario(request.getTipoUsuario())
                .build();

        Usuario salvo = usuarioRepository.save(usuario);

        return mapToResponse(salvo);
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private UsuarioResponseDTO mapToResponse(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nome(usuario.getNome())
                .ra(usuario.getRa())
                .email(usuario.getEmail())
                .tipoUsuario(usuario.getTipoUsuario())
                .build();
    }
}
