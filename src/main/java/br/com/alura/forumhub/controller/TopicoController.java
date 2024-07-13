package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.topico.DTO.DadosAtualizacaoTopicoDTO;
import br.com.alura.forumhub.domain.topico.DTO.DadosCadastroTopicoDTO;
import br.com.alura.forumhub.domain.topico.DTO.DadosDetalhamentoTopicoDTO;
import br.com.alura.forumhub.domain.topico.DTO.DadosExclusaoTopicoDTO;
import br.com.alura.forumhub.domain.topico.GerenciadorTopicoService;
import br.com.alura.forumhub.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    GerenciadorTopicoService topicosGerenciador;
    @Autowired
    TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity criarTopico(@RequestBody @Valid DadosCadastroTopicoDTO dados) {
        var topico = topicosGerenciador.criarTopico(dados);

        return ResponseEntity.ok(topico);

    }

    @GetMapping
    public ResponseEntity listarTopicos(){
        var topicos = repository.findAll().stream()
                .map(DadosDetalhamentoTopicoDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopicoDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id, @RequestBody  @Valid DadosExclusaoTopicoDTO dados) {
        topicosGerenciador.excluir(dados, id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopicoDTO dados) {
        var topico = repository.getReferenceById(id);
        topico.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopicoDTO(topico));
    }
}
