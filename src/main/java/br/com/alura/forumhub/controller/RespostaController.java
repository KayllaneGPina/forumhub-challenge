package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.resposta.DTO.DadosCriacaoRespostaDTO;
import br.com.alura.forumhub.domain.resposta.DTO.DadosExclusaoRespostaDTO;
import br.com.alura.forumhub.domain.resposta.GerenciadorRespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    GerenciadorRespostaService respostaGen;

    @PostMapping
    @Transactional
    public ResponseEntity criarResposta(@RequestBody @Valid DadosCriacaoRespostaDTO dados) {
        var resposta = respostaGen.criarResposta(dados);

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirResposta(@PathVariable Long id, @RequestBody @Valid DadosExclusaoRespostaDTO dados) {
        respostaGen.excluir(dados, id);

        return ResponseEntity.noContent().build();
    }
}
