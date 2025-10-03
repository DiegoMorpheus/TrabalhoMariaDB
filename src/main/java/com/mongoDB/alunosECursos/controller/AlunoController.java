package com.mongoDB.alunosECursos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mongoDB.alunosECursos.model.Aluno;
import com.mongoDB.alunosECursos.model.Curso;
import com.mongoDB.alunosECursos.repository.AlunoRepository;
import com.mongoDB.alunosECursos.repository.CursoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        if (aluno.getCurso() != null && aluno.getCurso().getId() != null) {
            Optional<Curso> curso = cursoRepository.findById(aluno.getCurso().getId());
            if (curso.isPresent()) {
                aluno.setCurso(curso.get());
                Aluno salvo = alunoRepository.save(aluno);
                return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        return alunoRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno dadosAtualizados) {
        return alunoRepository.findById(id)
            .map(aluno -> {
                aluno.setNome(dadosAtualizados.getNome());
                aluno.setEmail(dadosAtualizados.getEmail());
                aluno.setDataNascimento(dadosAtualizados.getDataNascimento());

                if (dadosAtualizados.getCurso() != null && dadosAtualizados.getCurso().getId() != null) {
                    Optional<Curso> curso = cursoRepository.findById(dadosAtualizados.getCurso().getId());
                    curso.ifPresent(aluno::setCurso);
                }

                return ResponseEntity.ok(alunoRepository.save(aluno));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
