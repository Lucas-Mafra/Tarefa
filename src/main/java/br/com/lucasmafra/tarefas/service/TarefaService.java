package br.com.lucasmafra.tarefas.service;

import java.util.List;

import br.com.lucasmafra.tarefas.dto.tarefa.CreateTarefaDTO;
import br.com.lucasmafra.tarefas.dto.tarefa.TarefaResponseDTO;
import br.com.lucasmafra.tarefas.dto.tarefa.UpdateTarefaDTO;

public interface TarefaService {

    TarefaResponseDTO createTarefa(CreateTarefaDTO dto);

    TarefaResponseDTO getTarefaById(long tarefaId);

    List<TarefaResponseDTO> getTarefas();

    TarefaResponseDTO updateTarefa(long tarefaId, UpdateTarefaDTO dto);

    void deleteTarefaById(long tarefaId);

    void deleteTarefas();
}
