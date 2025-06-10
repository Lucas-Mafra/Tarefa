package br.com.lucasmafra.tarefas.controller;

import br.com.lucasmafra.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
                Map.of("tarefas", tarefaRepository.findAll())
        );
    }


}
