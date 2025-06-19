package br.com.lucasmafra.tarefas.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.lucasmafra.tarefas.model.Tarefa;
import br.com.lucasmafra.tarefas.service.TarefaService;
import jakarta.validation.Valid;

@Controller
public class TarefaController {

    @Autowired
    public TarefaService tarefaService;

    @GetMapping("/")
    public ModelAndView listar() {
        return new ModelAndView(
                "tarefas/listar",
                Map.of("tarefas", tarefaService.listarTarefas(Sort.by("deadline")))
        );
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        return new ModelAndView("tarefas/formulario", Map.of("tarefa", new Tarefa()));
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Tarefa tarefa, BindingResult result) {
        if (result.hasErrors())
            return "tarefas/formulario";

        tarefaService.criar(tarefa);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        var tarefa = tarefaService.encontrarPorId(id);
        return new ModelAndView("tarefas/formulario", Map.of("tarefa", tarefa));
    }

    @PostMapping("/editar/{id}")
    public String editar(@Valid Tarefa tarefa, BindingResult result) {
        if (result.hasErrors())
            return "tarefas/formulario";

        tarefaService.atualizar(tarefa);
        return "redirect:/";
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        var tarefa = tarefaService.encontrarPorId(id);
        return new ModelAndView("tarefas/excluir", Map.of("tarefa", tarefa));
    }

    @PostMapping("/excluir/{id}")
    public String excluir(Tarefa tarefa) {
        tarefaService.excluir(tarefa.getId());
        return "redirect:/";
    }

    @PostMapping("/finalizar/{id}")
    public String finalizar(@PathVariable Long id) {
        tarefaService.finalizar(id);
        return "redirect:/";
    }

}
