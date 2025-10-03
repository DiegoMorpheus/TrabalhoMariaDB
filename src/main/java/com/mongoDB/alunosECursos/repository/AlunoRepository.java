package com.mongoDB.alunosECursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mongoDB.alunosECursos.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {}

