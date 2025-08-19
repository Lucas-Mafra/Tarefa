package br.com.lucasmafra.tarefas.controller;

import java.util.List;

import br.com.lucasmafra.tarefas.dto.tarefa.CreateTarefaDTO;
import br.com.lucasmafra.tarefas.dto.tarefa.UpdateTarefaDTO;
import br.com.lucasmafra.tarefas.dto.tarefa.TarefaResponseDTO;
import br.com.lucasmafra.tarefas.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Tarefa Controller", description = "This REST controller provides services to manage tasks in the Tarefas application")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    @Operation(summary = "List all tasks", description = "Provide all tasks available in the Tarefa application")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks")
    })
    public ResponseEntity<List<TarefaResponseDTO>> list() {
        return ResponseEntity.ok(tarefaService.getTarefas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID", description = "Provide task details for the supplied task ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved task"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TarefaResponseDTO> getTarefaById(
            @Parameter(description = "ID of the task to retrieve", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.getTarefaById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new task", description = "Create a new task in the Tarefa application")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<TarefaResponseDTO> create(
            @Parameter(description = "Task object to be created", required = true)
            @Valid @RequestBody CreateTarefaDTO dto) {
        TarefaResponseDTO newTarefa = tarefaService.createTarefa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTarefa);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task by ID", description = "Updates the task details for the supplied task ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task successfully updated"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<TarefaResponseDTO> update(
            @Parameter(description = "ID of the task to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated task object", required = true)
            @Valid @RequestBody UpdateTarefaDTO dto) {
        TarefaResponseDTO updated = tarefaService.updateTarefa(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by ID", description = "Deletes the task details for the supplied task ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the task to delete", required = true)
            @PathVariable Long id) {
        tarefaService.deleteTarefaById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete all tasks", description = "Deletes all tasks from the Tarefa application")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "All tasks successfully deleted")
    })
    public ResponseEntity<Void> deleteAll() {
        tarefaService.deleteTarefas();
        return ResponseEntity.noContent().build();
    }
}
