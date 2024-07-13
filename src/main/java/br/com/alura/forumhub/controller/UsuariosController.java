package br.com.alura.forumhub.controller;


import br.com.alura.forumhub.domain.usuario.DTO.DadosDetalhamentoUsuariosDTO;
import br.com.alura.forumhub.domain.usuario.DTO.DadosExclusaoUsuarioDTO;
import br.com.alura.forumhub.domain.usuario.GerenciadorUsuarioService;
import br.com.alura.forumhub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    GerenciadorUsuarioService userGen;

    @GetMapping
    public ResponseEntity listarUsuarios(){
        List<DadosDetalhamentoUsuariosDTO> usuarios = repository.findAllByAtivoTrue()
                .stream().map(DadosDetalhamentoUsuariosDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirUsuario(@PathVariable Long id, @RequestBody @Valid DadosExclusaoUsuarioDTO dados){
        userGen.excluir(id, dados);
        return ResponseEntity.noContent().build();
    }
}
