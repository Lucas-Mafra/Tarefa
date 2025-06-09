package br.com.lucasmafra.tarefas.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false)
    private boolean finished;

    @Column(nullable = false)
    private LocalDate fineshedAt;

    public Tarefa() {
        this.createdAt = LocalDateTime.now();
        this.finished = false;
        this.fineshedAt = null;
    }



}