package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.curso.CursoRepository;
import br.com.alura.forumhub.domain.curso.DTO.DadosDetalhamentoCursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoRepository repository;

    @GetMapping
    public ResponseEntity listarCursos() {
        List<DadosDetalhamentoCursoDTO> cursos = repository.findAll().stream()
                .map(curso -> new DadosDetalhamentoCursoDTO(curso))
                .collect(Collectors.toList());
        System.out.println(cursos);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharCurso(@PathVariable Long id) {
        var curso = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoCursoDTO(curso));
    }

}
