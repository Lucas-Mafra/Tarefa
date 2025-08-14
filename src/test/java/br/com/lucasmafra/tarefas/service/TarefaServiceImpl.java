package br.com.lucasmafra.tarefas.service;

import br.com.lucasmafra.tarefas.model.Tarefa;
import br.com.lucasmafra.tarefas.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceImplTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaServiceImpl tarefaService;

    private Tarefa tarefa1;
    private Tarefa tarefa2;

    @BeforeEach
    void setup() {
        tarefa1 = new Tarefa();
        tarefa1.setId(1L);
        tarefa1.setTitle("Tarefa 1");

        tarefa2 = new Tarefa();
        tarefa2.setId(2L);
        tarefa2.setTitle("Tarefa 2");
    }

    @Test
    void shouldCreateTarefa() {
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa1);

        Tarefa result = tarefaService.createTarefa(tarefa1);

        assertEquals("Tarefa 1", result.getTitle());
        verify(tarefaRepository, times(1)).save(tarefa1);
    }

    @Test
    void shouldGetTarefaById() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa1));

        Tarefa result = tarefaService.getTarefaById(1L);

        assertEquals("Tarefa 1", result.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenTarefaNotFound() {
        when(tarefaRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> tarefaService.getTarefaById(3L));
    }

    @Test
    void shouldGetAllTarefas() {
        when(tarefaRepository.findAll()).thenReturn(Arrays.asList(tarefa1, tarefa2));

        List<Tarefa> tarefas = tarefaService.getTarefas();

        assertEquals(2, tarefas.size());
    }

    @Test
    void shouldUpdateTarefa() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa1));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa1);

        tarefa1.setTitle("Tarefa Atualizada");
        tarefaService.updateTarefa(1L, tarefa1);

        verify(tarefaRepository, times(1)).save(tarefa1);
    }

    @Test
    void shouldDeleteTarefaById() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa1));

        tarefaService.deleteTarefaById(1L);

        verify(tarefaRepository, times(1)).delete(tarefa1);
    }

    @Test
    void shouldDeleteAllTarefas() {
        doNothing().when(tarefaRepository).deleteAll();

        tarefaService.deleteTarefas();

        verify(tarefaRepository, times(1)).deleteAll();
    }
}
