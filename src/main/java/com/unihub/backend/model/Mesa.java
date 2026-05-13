package com.unihub.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mesas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMesa;

    @Column(nullable = false)
    private String nome; // Ex: Mesa 1

    @Column(nullable = false)
    private String lado; // Ex: left / right

    @Column(nullable = false)
    private Boolean disponivel;
}
