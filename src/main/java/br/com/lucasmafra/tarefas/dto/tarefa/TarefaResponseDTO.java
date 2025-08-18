package br.com.lucasmafra.tarefas.dto.tarefa;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TarefaResponseDTO(
        Long id,
        String title,
        LocalDateTime createdAt,
        LocalDate deadline,
        boolean finished,
        LocalDate finishedAt
) {}
