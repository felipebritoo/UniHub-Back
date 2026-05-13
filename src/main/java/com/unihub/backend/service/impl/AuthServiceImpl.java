package com.unihub.backend.service.impl;

import com.unihub.backend.dto.CadastroRequestDTO;
import com.unihub.backend.dto.LoginRequestDTO;
import com.unihub.backend.dto.UsuarioResponseDTO;
import com.unihub.backend.exception.BusinessException;
import com.unihub.backend.model.Usuario;
import com.unihub.backend.repository.UsuarioRepository;
import com.unihub.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioResponseDTO cadastrar(CadastroRequestDTO request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException("E-mail já cadastrado");
        }
        if (usuarioRepository.findByRa(request.getRa()).isPresent()) {
            throw new BusinessException("RA já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(request.getNome() != null ? request.getNome() : "Usuário")
                .ra(request.getRa())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .tipoUsuario(request.getTipoUsuario())
                .build();

        Usuario salvo = usuarioRepository.save(usuario);

        return mapToResponse(salvo);
    }

    @Override
    public UsuarioResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getIdentifier())
                .orElseGet(() -> usuarioRepository.findByRa(request.getIdentifier())
                .orElseThrow(() -> new BusinessException("Credenciais inválidas")));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new BusinessException("Credenciais inválidas");
        }

        return mapToResponse(usuario);
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
