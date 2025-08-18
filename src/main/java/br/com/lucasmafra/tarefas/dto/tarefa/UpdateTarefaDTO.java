package br.com.lucasmafra.tarefas.dto.tarefa;

import java.time.LocalDate;

public record UpdateTarefaDTO (String title, LocalDate deadline, boolean finished){
}
