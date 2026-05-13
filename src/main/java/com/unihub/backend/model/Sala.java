package com.unihub.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSala;

    @Column(nullable = false, unique = true)
    private String nome; // Ex: L-101

    @Column(nullable = false)
    private String predio; // Ex: L

    @Column(nullable = false)
    private Integer capacidade; // Ex: 12 (places)

    private String recursos; // Ex: "Projetor, Quadro branco" (Simplificado como String para este escopo)
}
