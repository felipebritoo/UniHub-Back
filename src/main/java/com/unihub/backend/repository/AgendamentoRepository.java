package com.unihub.backend.repository;

import com.unihub.backend.enums.StatusAgendamento;
import com.unihub.backend.model.Agendamento;
import com.unihub.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByUsuario(Usuario usuario);

    Optional<Agendamento> findBySala_IdSalaAndDataAndHorarioAndStatusNot(
            Long idSala, LocalDate data, LocalTime horario, StatusAgendamento status);

    Optional<Agendamento> findByMesa_IdMesaAndDataAndHorarioAndStatusNot(
            Long idMesa, LocalDate data, LocalTime horario, StatusAgendamento status);
}
