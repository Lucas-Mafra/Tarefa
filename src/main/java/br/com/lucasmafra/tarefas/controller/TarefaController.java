package br.com.lucasmafra.tarefas.controller;

import br.com.lucasmafra.tarefas.model.Tarefa;
import br.com.lucasmafra.tarefas.repository.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class TarefaController {

    @Autowired
    public TarefaRepository tarefaRepository;

    @GetMapping("/")
    public ModelAndView listar() {
        return new ModelAndView(
                "tarefas/listar",
                Map.of("tarefas", tarefaRepository.findAll(Sort.by("deadline")))
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

        tarefaRepository.save(tarefa);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        var tarefa = tarefaRepository.findById(id);
        // Se a tarefa não for encontrada, retorna um erro 404
        if (tarefa.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new ModelAndView("tarefas/formulario", Map.of("tarefa", tarefa.get()));
    }

    @PostMapping("/editar/{id}")
    public String editar(@Valid Tarefa tarefa, BindingResult result) {
        if (result.hasErrors())
            return "tarefas/formulario";

        tarefaRepository.save(tarefa);
        return "redirect:/";
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        var tarefa = tarefaRepository.findById(id);
        if(tarefa.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ModelAndView("tarefas/excluir", Map.of("tarefa", tarefa.get()));
    }

    @PostMapping("/excluir/{id}")
    public String excluir(Tarefa tarefa) {
        tarefaRepository.delete(tarefa);
        return "redirect:/";
    }

    @PostMapping("/finalizar/{id}")
    public String finalizar(@PathVariable Long id) {
        var tarefa = tarefaRepository.findById(id);
        if (tarefa.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        tarefa.get().TarefaFinalizada();
        tarefaRepository.save(tarefa.get());
        return "redirect:/";
    }

}
