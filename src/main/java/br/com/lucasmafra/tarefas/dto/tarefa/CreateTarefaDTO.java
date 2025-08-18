package br.com.lucasmafra.tarefas.dto.tarefa;

import java.time.LocalDate;

public record CreateTarefaDTO(String title, LocalDate deadline) { }
