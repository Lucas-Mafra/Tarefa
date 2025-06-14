# Tarefa

Este é um projeto Spring Boot simples para gerenciamento de tarefas.

## Visão Geral

O projeto permite criar, listar e possivelmente atualizar e excluir tarefas. Ele usa Spring Boot, Spring Data JPA e um banco de dados embutido, H2, para persistência. O frontend é renderizado usando templates Thymeleaf.

## Pré-requisitos

- Java 17 ou superior
- Maven

## Como Executar

1.  Clone o repositório:

    ```bash
    git clone <URL do seu repositório>
    ```
2.  Navegue até o diretório do projeto:

    ```bash
    cd Tarefa
    ```
3.  Execute o aplicativo Spring Boot usando Maven:

    ```bash
    mvn spring-boot:run
    ```

O aplicativo estará disponível em `http://localhost:8080`.

## Endpoints

-   `GET /`: Lista todas as tarefas.
-   `GET /cadastrar`: Exibe o formulário para cadastrar uma nova tarefa.

## Tecnologias Utilizadas

-   Spring Boot
-   Spring Data JPA
-   Thymeleaf
-   Maven
-   H2 Database (ou outro banco de dados configurado)
-   Bootstrap

## Próximos Passos

-   Implementar a funcionalidade de adicionar novas tarefas.
-   Implementar a funcionalidade de editar e excluir tarefas.
-   Adicionar testes unitários e de integração.
-   Melhorar a interface do usuário.
