package br.com.alura.forumhub.domain.curso.DTO;

import br.com.alura.forumhub.domain.curso.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCursoDTO(

        @NotBlank
        String nome,

        @NotNull
        Categoria categoria
) {}
