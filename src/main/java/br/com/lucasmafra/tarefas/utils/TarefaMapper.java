package br.com.lucasmafra.tarefas.utils;

import br.com.lucasmafra.tarefas.dto.tarefa.CreateTarefaDTO;
import br.com.lucasmafra.tarefas.dto.tarefa.TarefaResponseDTO;
import br.com.lucasmafra.tarefas.dto.tarefa.UpdateTarefaDTO;
import br.com.lucasmafra.tarefas.model.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);
    Tarefa toEntity(CreateTarefaDTO dto);
    TarefaResponseDTO toResponse(Tarefa tarefa);
    void updateEntityFromDto(UpdateTarefaDTO dto, @MappingTarget Tarefa tarefa);
}
