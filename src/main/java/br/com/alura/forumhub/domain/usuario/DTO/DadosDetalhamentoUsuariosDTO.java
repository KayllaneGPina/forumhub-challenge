package br.com.alura.forumhub.domain.usuario.DTO;

import br.com.alura.forumhub.domain.usuario.Usuario;

public record DadosDetalhamentoUsuariosDTO(
        Long id,
        String nome,
        String email
) {
    public DadosDetalhamentoUsuariosDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
