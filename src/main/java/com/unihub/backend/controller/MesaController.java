package com.unihub.backend.controller;

import com.unihub.backend.dto.MesaRequestDTO;
import com.unihub.backend.dto.MesaResponseDTO;
import com.unihub.backend.service.MesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    @PostMapping
    public ResponseEntity<MesaResponseDTO> criar(@RequestBody @Valid MesaRequestDTO request) {
        return new ResponseEntity<>(mesaService.criar(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MesaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(mesaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mesaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MesaRequestDTO request) {
        return ResponseEntity.ok(mesaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        mesaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
