package br.com.alura.forumhub.domain.resposta.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriacaoRespostaDTO(
        @NotBlank
        String mensagem,

        @NotNull
        Long topico_id,

        @NotNull
        Long usuario_id
) {}
