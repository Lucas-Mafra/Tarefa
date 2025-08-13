package br.com.lucasmafra.tarefas.controller;

import java.util.List;
import java.util.Map;

import br.com.lucasmafra.tarefas.service.TarefaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.com.lucasmafra.tarefas.model.Tarefa;
import br.com.lucasmafra.tarefas.service.TarefaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefaController {

    @Autowired
    public TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<Tarefa>>list(){
        List<Tarefa> tarefasList = tarefaService.getTarefas();
        return ResponseEntity.ok(tarefasList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id){
        var tarefa = tarefaService.getTarefaById(id);
        return ResponseEntity.ok(tarefa);
    }

    @PostMapping
    public ResponseEntity<Tarefa> create(@Valid @RequestBody Tarefa tarefa){
        Tarefa newTarefa = tarefaService.createTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa){
        tarefa.setId(id);
        tarefaService.updateTarefa(id, tarefa);
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tarefa> delete(@PathVariable Long id){
        tarefaService.deleteTarefaById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAll(){
        tarefaService.deleteTarefas();
        return ResponseEntity.noContent().build();
    }

}
