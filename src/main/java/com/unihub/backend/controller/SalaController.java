package com.unihub.backend.controller;

import com.unihub.backend.dto.SalaRequestDTO;
import com.unihub.backend.dto.SalaResponseDTO;
import com.unihub.backend.service.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @PostMapping
    public ResponseEntity<SalaResponseDTO> criar(@RequestBody @Valid SalaRequestDTO request) {
        return new ResponseEntity<>(salaService.criar(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(salaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.buscarPorId(id));
    }

    @GetMapping("/predio/{predio}")
    public ResponseEntity<List<SalaResponseDTO>> buscarPorPredio(@PathVariable String predio) {
        return ResponseEntity.ok(salaService.buscarPorPredio(predio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid SalaRequestDTO request) {
        return ResponseEntity.ok(salaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        salaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
