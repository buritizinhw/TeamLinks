# TeamLinks

API REST para gerenciamento de links organizados por projetos. Cada projeto possui N links e cada link pode ser classificado com tags.

## Tecnologias

- Java 17
- Spring Boot 4.0.4
- Spring Data JPA
- PostgreSQL 16
- Docker & Docker Compose
- Swagger / OpenAPI 3
- Lombok
- Maven

## Pré-requisitos

- [Docker](https://docs.docker.com/get-docker/) e [Docker Compose](https://docs.docker.com/compose/install/)

## Iniciando a aplicação

1. Crie o arquivo `.env` na raiz do projeto a partir do exemplo:

```bash
cp .env.example .env
```

2. Preencha as variáveis no `.env`:

```env
DATABASE_HOST=postgres
DATABASE_PORT=5432
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=password
DATABASE_NAME=postgres
```

4. Suba os containers:

```bash
docker compose up --build
```

A API estará disponível em `http://localhost:8080` e a documentação interativa em `http://localhost:8080/swagger-ui.html`.

## Dados iniciais

Ao iniciar com o banco vazio, a aplicação popula automaticamente os seguintes dados:

### Tags

`backend`, `frontend`, `repositorio`, `design`, `ia`, `comunicacao`, `testes`, `deploy`, `documentacao`

### Projetos

| Projeto | Descrição |
|---|---|
| TeamLinks | Sistema de gerenciamento de links para projetos |
| E-commerce API | API REST para plataforma de e-commerce |

### Links

| Projeto | Link | Tags |
|---|---|---|
| TeamLinks | GitHub - TeamLinks | `repositorio`, `backend` |
| TeamLinks | Documentação Spring Boot | `documentacao`, `backend` |
| TeamLinks | Swagger UI | `documentacao`, `backend` |
| E-commerce API | GitLab | `repositorio`, `backend` |
| E-commerce API | Figma - Protótipo | `design`, `frontend` |

## Endpoints

### Projetos — `/api/projects`

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/projects` | Criar projeto |
| `GET` | `/api/projects?page=0&size=10` | Listar projetos (paginado) |
| `GET` | `/api/projects/{id}` | Buscar projeto por ID |
| `PUT` | `/api/projects/{id}` | Atualizar projeto |
| `DELETE` | `/api/projects/{id}` | Deletar projeto e seus links |
| `GET` | `/api/projects/{id}/links?page=0&size=10` | Listar links do projeto (paginado) |

### Links — `/api/links`

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/links/project/{projectId}` | Criar link em um projeto |
| `GET` | `/api/links/{id}` | Buscar link por ID |
| `PUT` | `/api/links/{id}` | Atualizar link |
| `DELETE` | `/api/links/{id}` | Deletar link |

### Tags — `/api/tags`

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/tags` | Criar tag |
| `GET` | `/api/tags?page=0&size=20` | Listar tags (paginado) |
| `GET` | `/api/tags/{id}` | Buscar tag por ID |
| `PUT` | `/api/tags/{id}` | Atualizar tag |
| `DELETE` | `/api/tags/{id}` | Deletar tag |

### Paginacao

Os endpoints de listagem aceitam os seguintes query params:

| Parametro | Descricao | Padrao |
|---|---|---|
| `page` | Numero da pagina (comeca em 0) | `0` |
| `size` | Itens por pagina | `10` ou `20` |
| `sort` | Ordenacao (ex: `name,asc`) | nenhum |

