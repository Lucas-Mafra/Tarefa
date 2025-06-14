package br.com.lucasmafra.tarefas.service;

import br.com.lucasmafra.tarefas.model.Tarefa;
import br.com.lucasmafra.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    public TarefaRepository tarefaRepository;

    public Tarefa criar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Tarefa tarefaAtualizada) {
        Tarefa tarefaPersistida = this.encontrarPorId(tarefaAtualizada.getId());

        tarefaPersistida.setTitle(tarefaAtualizada.getTitle());
        tarefaPersistida.setDeadline(tarefaAtualizada.getDeadline());

        return tarefaRepository.save(tarefaPersistida);
    }

    public void finalizar(Long id) {
        Tarefa tarefa = this.encontrarPorId(id);
        tarefa.tarefaFinalizada();
        tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas(Sort sort) {
        return tarefaRepository.findAll(sort);
    }

    public Tarefa encontrarPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Tarefa não encontrada com o ID: " + id)
        );
    }

    public void excluir(Long id) {
        Tarefa tarefa = this.encontrarPorId(id);
        tarefaRepository.delete(tarefa);
    }


}
