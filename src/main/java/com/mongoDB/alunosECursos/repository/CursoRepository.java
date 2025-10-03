package com.mongoDB.alunosECursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mongoDB.alunosECursos.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {}