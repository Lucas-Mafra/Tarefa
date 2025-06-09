package br.com.lucasmafra.tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
        var modelAndView = new ModelAndView("home");
        modelAndView.addObject("nome", "Lucas Mafra");
        return modelAndView;
    }


}
