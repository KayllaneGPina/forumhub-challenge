package br.com.alura.forumhub.domain.usuario;

import br.com.alura.forumhub.domain.usuario.DTO.DadosExclusaoUsuarioDTO;
import br.com.alura.forumhub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GerenciadorUsuarioService {
    @Autowired
    UsuarioRepository repository;

    public void excluir(Long id, DadosExclusaoUsuarioDTO dados) {

        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isEmpty()) {
            throw new ValidacaoException("Nenhum usuário encontrado com o id informado.");
        }

        if (!usuario.get().getSenha().equals(dados.senha()) && !usuario.get().getEmail().equals(dados.email())) {
            throw new ValidacaoException("Não é possível excluir o usuário de outras pessoas!");
        }

        usuario.get().excluir();
    }
}
