package com.mongoDB.alunosECursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mongoDB.alunosECursos.model.Curso;
import com.mongoDB.alunosECursos.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody Curso curso) {
        Curso salvo = cursoRepository.save(curso);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar(@PathVariable Long id) {
        return cursoRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso cursoAtualizado) {
        return cursoRepository.findById(id)
            .map(curso -> {
                curso.setNome(cursoAtualizado.getNome());
                curso.setCargaHoraria(cursoAtualizado.getCargaHoraria());
                return ResponseEntity.ok(cursoRepository.save(curso));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
