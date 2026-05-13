package com.unihub.backend.service;

import com.unihub.backend.dto.CadastroRequestDTO;
import com.unihub.backend.dto.LoginRequestDTO;
import com.unihub.backend.dto.UsuarioResponseDTO;

public interface AuthService {
    UsuarioResponseDTO cadastrar(CadastroRequestDTO request);
    UsuarioResponseDTO login(LoginRequestDTO request);
}
