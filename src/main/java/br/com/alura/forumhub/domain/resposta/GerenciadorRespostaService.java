package br.com.alura.forumhub.domain.resposta;

import br.com.alura.forumhub.domain.resposta.DTO.DadosCriacaoRespostaDTO;
import br.com.alura.forumhub.domain.resposta.DTO.DadosDetalhamentoRespostaDTO;
import br.com.alura.forumhub.domain.resposta.DTO.DadosExclusaoRespostaDTO;
import br.com.alura.forumhub.domain.topico.TopicoRepository;
import br.com.alura.forumhub.domain.usuario.UsuarioRepository;
import br.com.alura.forumhub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GerenciadorRespostaService {

    @Autowired
    RespostaRepository repository;

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    TopicoRepository topicoRepository;


    public DadosDetalhamentoRespostaDTO criarResposta(DadosCriacaoRespostaDTO dados) {

        if (!topicoRepository.existsById(dados.topico_id())) {
            throw new ValidacaoException("O tópico informado não existe!");
        }
        if (!userRepository.existsById(dados.usuario_id())) {
            throw new ValidacaoException("O usuário informado não existe!");
        }

        var usuario = userRepository.getReferenceById(dados.usuario_id());
        var topico = topicoRepository.getReferenceById(dados.topico_id());

        var resposta = new Resposta(topico, usuario, dados);

        repository.save(resposta);
        return new DadosDetalhamentoRespostaDTO(resposta);
    }

    public String excluir(DadosExclusaoRespostaDTO dados, Long id) {
        Optional<Resposta> resposta = repository.findById(id);
        if (resposta.isEmpty()) {
            throw new ValidacaoException("Esta resposta não pôde ser deletada, pois não existe!");
        }
        if (!resposta.get().getUsuario().getEmail().equals(dados.email()) && !resposta.get().getTopico().getUsuario().getEmail().equals(dados.senha())) {
            throw new ValidacaoException("Você só pode excluir respostas de sua autoria, a menos que seja o criador do tópico.");
        }

        repository.delete(resposta.get());

        return "Resposta: " + resposta.get() + " deletada com sucesso.";
    }
}
