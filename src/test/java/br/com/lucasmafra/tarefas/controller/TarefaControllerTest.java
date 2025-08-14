package br.com.lucasmafra.tarefas.controller;

import br.com.lucasmafra.tarefas.model.Tarefa;
import br.com.lucasmafra.tarefas.service.TarefaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class TarefaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TarefaService tarefaService;

    @InjectMocks
    private TarefaController tarefaController;

    private ObjectMapper objectMapper;

    private Tarefa tarefa1;
    private Tarefa tarefa2;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(tarefaController).build();

        tarefa1 = new Tarefa();
        tarefa1.setId(1L);
        tarefa1.setTitle("Tarefa 1");

        tarefa2 = new Tarefa();
        tarefa2.setId(2L);
        tarefa2.setTitle("Tarefa 2");
    }

    @Test
    void shouldListAllTarefas() throws Exception {
        List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2);
        Mockito.when(tarefaService.getTarefas()).thenReturn(tarefas);

        mockMvc.perform(get("/api/v1/tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].titulo", is("Tarefa 1")));
    }

    @Test
    void shouldGetTarefaById() throws Exception {
        Mockito.when(tarefaService.getTarefaById(1L)).thenReturn(tarefa1);

        mockMvc.perform(get("/api/v1/tarefas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Tarefa 1")));
    }

    @Test
    void shouldCreateTarefa() throws Exception {
        Mockito.when(tarefaService.createTarefa(Mockito.any(Tarefa.class))).thenReturn(tarefa1);

        mockMvc.perform(post("/api/v1/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarefa1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo", is("Tarefa 1")));
    }

    @Test
    void shouldUpdateTarefa() throws Exception {
        Mockito.doNothing().when(tarefaService).updateTarefa(Mockito.eq(1L), Mockito.any(Tarefa.class));

        mockMvc.perform(put("/api/v1/tarefas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarefa1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Tarefa 1")));
    }

    @Test
    void shouldDeleteTarefa() throws Exception {
        Mockito.doNothing().when(tarefaService).deleteTarefaById(1L);

        mockMvc.perform(delete("/api/v1/tarefas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteAllTarefas() throws Exception {
        Mockito.doNothing().when(tarefaService).deleteTarefas();

        mockMvc.perform(delete("/api/v1/tarefas/delete"))
                .andExpect(status().isNoContent());
    }
}
