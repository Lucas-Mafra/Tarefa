package br.com.lucasmafra.tarefas.service;

import java.util.List;
import java.util.Optional;

import br.com.lucasmafra.tarefas.model.Tarefa;

public interface TarefaService {

    Tarefa createTarefa(Tarefa tarefa);

    Tarefa getTarefaById(long tarefaid);

    List<Tarefa> getTarefas();

    void updateTarefa(long tarefaId, Tarefa tarefa);

    void deleteTarefaById(long tarefaId);

    void deleteTarefas();
}
