package br.com.lucasmafra.tarefas.service;

import br.com.lucasmafra.tarefas.dto.tarefa.CreateTarefaDTO;
import br.com.lucasmafra.tarefas.dto.tarefa.TarefaResponseDTO;
import br.com.lucasmafra.tarefas.dto.tarefa.UpdateTarefaDTO;
import br.com.lucasmafra.tarefas.model.Tarefa;
import br.com.lucasmafra.tarefas.repository.TarefaRepository;
import br.com.lucasmafra.tarefas.utils.TarefaMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    @Override
    @Transactional
    public TarefaResponseDTO createTarefa(CreateTarefaDTO dto) {
        Tarefa tarefa = tarefaMapper.toEntity(dto);
        Tarefa saved = tarefaRepository.save(tarefa);
        return tarefaMapper.toResponse(saved);
    }

    @Override
    public TarefaResponseDTO getTarefaById(long tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
        return tarefaMapper.toResponse(tarefa);
    }

    @Override
    public List<TarefaResponseDTO> getTarefas() {
        return tarefaRepository.findAll()
                .stream()
                .map(tarefaMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public TarefaResponseDTO updateTarefa(long tarefaId, UpdateTarefaDTO dto) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));

        tarefaMapper.updateEntityFromDto(dto, tarefa);

        Tarefa updated = tarefaRepository.save(tarefa);
        return tarefaMapper.toResponse(updated);
    }


    @Override
    @Transactional
    public void deleteTarefaById(long tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
        tarefaRepository.delete(tarefa);
    }

    @Override
    @Transactional
    public void deleteTarefas() {
        tarefaRepository.deleteAll();
    }
}
