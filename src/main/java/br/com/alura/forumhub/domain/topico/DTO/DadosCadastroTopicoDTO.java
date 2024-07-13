package br.com.alura.forumhub.domain.topico.DTO;

import br.com.alura.forumhub.domain.topico.TopicoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        TopicoStatus status,
        @NotNull
        Long curso_id,
        @NotNull
        Long usuario_id
) {}
