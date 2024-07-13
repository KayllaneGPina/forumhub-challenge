package br.com.alura.forumhub.domain.topico;

import br.com.alura.forumhub.domain.curso.CursoRepository;
import br.com.alura.forumhub.domain.topico.DTO.DadosCadastroTopicoDTO;
import br.com.alura.forumhub.domain.topico.DTO.DadosDetalhamentoTopicoDTO;
import br.com.alura.forumhub.domain.topico.DTO.DadosExclusaoTopicoDTO;
import br.com.alura.forumhub.domain.usuario.UsuarioRepository;
import br.com.alura.forumhub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GerenciadorTopicoService {

    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    UsuarioRepository userRepository;
    @Autowired
    TopicoRepository topicoRepository;

    public DadosDetalhamentoTopicoDTO criarTopico(DadosCadastroTopicoDTO dados) {

        if (!cursoRepository.existsById(dados.curso_id())) {
            throw new ValidacaoException("Curso não existe no sistema!");
        }

        if (!userRepository.existsById(dados.usuario_id())) {
            throw new ValidacaoException("Usuario não existe!");
        }

        var usuario = userRepository.getReferenceById(dados.usuario_id());
        var curso = cursoRepository.getReferenceById(dados.curso_id());

        var topico = new Topico(usuario, curso, dados);

        topicoRepository.save(topico);
        return new DadosDetalhamentoTopicoDTO(topico);
    }

    public String excluir(DadosExclusaoTopicoDTO dados, Long id) {

        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isEmpty()) {
            throw new ValidacaoException("Nenhum tópico encontrado com o id informado.");
        }
        if (!(topico.get().getUsuario().getEmail().equals(dados.email())) && !topico.get().getUsuario().getSenha().equals(dados.senha())) {
            throw new ValidacaoException("Não é possível excluir tópicos que não são seus!");
        } else {
            topicoRepository.delete(topico.get());
            return "Tópico " + topico.get() + " excluído!";
        }
    }
}
