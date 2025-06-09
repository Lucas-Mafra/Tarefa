package br.com.lucasmafra.tarefas.repository;

import br.com.lucasmafra.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
