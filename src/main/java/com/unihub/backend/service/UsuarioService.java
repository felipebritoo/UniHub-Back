package com.unihub.backend.service;

import com.unihub.backend.dto.UsuarioRequestDTO;
import com.unihub.backend.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO);
    List<UsuarioResponseDTO> listarTodos();
}
