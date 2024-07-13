package br.com.alura.forumhub.domain.usuario.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosLoginDTO(
        @Email
        @NotBlank
        String email,

        @NotBlank
        String senha
) {
}
