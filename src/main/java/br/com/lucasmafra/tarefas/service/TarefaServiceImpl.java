package br.com.lucasmafra.tarefas.service;

import br.com.lucasmafra.tarefas.Utils.BeanUtilsHelper;
import br.com.lucasmafra.tarefas.model.Tarefa;
import br.com.lucasmafra.tarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    @Transactional
    public Tarefa createTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @Override
    public Tarefa getTarefaById(long tarefaId) {
        return tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
    }

    @Override
    public Iterable<Tarefa> getTarefas() {
        return tarefaRepository.findAll();
    }

    @Override
    @Transactional
    public void updateTarefa(long tarefaId, Tarefa tarefa) {
        Tarefa tarefaBD = this.getTarefaById(tarefaId);
        BeanUtilsHelper.copyNonNullProperties(tarefa, tarefaBD);
        tarefaRepository.save(tarefaBD);
    }

    @Override
    @Transactional
    public void deleteTarefaById(long tarefaId) {
        Tarefa tarefa = this.getTarefaById(tarefaId);
        tarefaRepository.delete(tarefa);
    }

    @Override
    @Transactional
    public void deleteTarefas() {
        tarefaRepository.deleteAll();
    }
}
