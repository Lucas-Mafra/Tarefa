package br.com.lucasmafra.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucasmafra.tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
