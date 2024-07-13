package br.com.alura.forumhub.domain.topico.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosExclusaoTopicoDTO(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {}
