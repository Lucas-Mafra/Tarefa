# Tarefa

Este é um projeto Spring Boot para gerenciamento de tarefas, permitindo criar, listar, editar, excluir e finalizar tarefas.

## Visão Geral

O projeto utiliza Spring Boot para o backend, Thymeleaf para renderização de templates no frontend e Spring Data JPA para persistência de dados. Ele organiza as tarefas com base em prazos e oferece uma interface simples para gerenciá-las.

## Funcionalidades

- Listar tarefas
- Cadastrar novas tarefas
- Editar tarefas existentes
- Excluir tarefas
- Finalizar tarefas

## Tecnologias Utilizadas

- **Java 17** ou superior
- **Spring Boot**
- **Spring Data JPA**
- **Thymeleaf**
- **Bootstrap** (para estilização)
- **H2 Database** (banco de dados em memória)

## Pré-requisitos

Certifique-se de ter instalado:

- Java 17 ou superior
- Maven
- Um IDE como IntelliJ IDEA ou Eclipse

## Como Executar

1. Clone o repositório:

    ```bash
    git clone https://github.com/Lucas-Mafra/Tarefa.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd Tarefa
    ```

3. Execute o projeto com Maven:

    ```bash
    mvn spring-boot:run
    ```

4. Acesse o aplicativo no navegador:

    ```
    http://localhost:8080
    ```

## Estrutura de Endpoints

- `GET /` - Lista todas as tarefas.
- `GET /cadastrar` - Exibe o formulário para cadastrar uma nova tarefa.
- `POST /cadastrar` - Salva uma nova tarefa.
- `GET /editar/{id}` - Exibe o formulário para editar uma tarefa.
- `POST /editar/{id}` - Atualiza uma tarefa existente.
- `GET /excluir/{id}` - Exibe a confirmação para excluir uma tarefa.
- `POST /excluir/{id}` - Exclui uma tarefa.
- `POST /finalizar/{id}` - Marca uma tarefa como finalizada.

## Próximos Passos

- Adicionar autenticação e autorização.
- Implementar testes unitários e de integração.
- Melhorar a interface do usuário.
- Adicionar suporte a bancos de dados externos (MySQL, PostgreSQL, etc.).

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## Licença

Este projeto está licenciado sob a licença MIT.