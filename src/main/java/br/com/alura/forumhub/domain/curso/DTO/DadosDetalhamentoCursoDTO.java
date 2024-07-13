package br.com.alura.forumhub.domain.curso.DTO;

import br.com.alura.forumhub.domain.curso.Categoria;
import br.com.alura.forumhub.domain.curso.Curso;

public record DadosDetalhamentoCursoDTO(
        Long id,
        String nome,
        Categoria categoria
) {
    public DadosDetalhamentoCursoDTO(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
