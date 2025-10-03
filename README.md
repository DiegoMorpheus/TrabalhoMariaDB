Tecnologias utilizadas
Java 17+

Spring Boot

Spring Data JPA

MariaDB

Maven

Postman (para testes)

Estrutura
src/
├── main/
│   ├── java/com/mongoDB/alunosECursos/
│   │   ├── model/         # Entidades JPA (Aluno, Curso)
│   │   ├── repository/    # Interfaces JpaRepository
│   │   ├── controller/    # Endpoints REST
│   │   └── AlunosECursosApplication.java
│   └── resources/
│       └── application.properties


Crie o banco no MariaDB:
CREATE DATABASE universidade;

Configure o application.properties:
spring.datasource.url=jdbc:mariadb://localhost:3306/universidade
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Endpoints principais
🔹 Cursos
POST /cursos – Criar curso

GET /cursos – Listar cursos

GET /cursos/{id} – Buscar curso por ID

PUT /cursos/{id} – Atualizar curso

DELETE /cursos/{id} – Excluir curso

 Alunos
POST /alunos – Criar aluno (vinculado a curso)

GET /alunos – Listar alunos

GET /alunos/{id} – Buscar aluno por ID

PUT /alunos/{id} – Atualizar aluno

DELETE /alunos/{id} – Excluir aluno

Testes com Postman
Exemplo para criar um aluno:

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "dataNascimento": "2000-01-01",
  "curso": {
    "id": 1
  }
}

