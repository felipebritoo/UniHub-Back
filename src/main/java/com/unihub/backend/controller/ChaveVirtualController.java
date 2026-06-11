package com.unihub.backend.controller;

import com.unihub.backend.dto.ChaveVirtualRequestDTO;
import com.unihub.backend.dto.ChaveVirtualResponseDTO;
import com.unihub.backend.service.ChaveVirtualService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chave-virtual")
@RequiredArgsConstructor
@Tag(name = "Chave Virtual", description = "Endpoints para funcionalidade de chave virtual de salas")
public class ChaveVirtualController {

    private final ChaveVirtualService chaveVirtualService;

    @PostMapping("/destravar")
    @Operation(summary = "Destrava uma sala remotamente", description = "Apenas usuários do tipo PROFESSOR podem destravar salas.")
    public ResponseEntity<ChaveVirtualResponseDTO> destravar(@Valid @RequestBody ChaveVirtualRequestDTO request) {
        return ResponseEntity.ok(chaveVirtualService.destravarSala(request));
    }
}
