package br.com.alura.forumhub.domain.resposta.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosExclusaoRespostaDTO(
        @NotBlank
        String email,

        @NotBlank
        String senha
) {}
