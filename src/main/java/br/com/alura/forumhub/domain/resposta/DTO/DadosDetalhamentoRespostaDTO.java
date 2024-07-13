package br.com.alura.forumhub.domain.resposta.DTO;

import br.com.alura.forumhub.domain.resposta.Resposta;
import br.com.alura.forumhub.domain.usuario.DTO.DadosDetalhamentoUsuariosDTO;

import java.time.OffsetDateTime;

public record DadosDetalhamentoRespostaDTO(
        Long id,
        String mensagem,
        Long topico_id,
        OffsetDateTime data,
        DadosDetalhamentoUsuariosDTO usuario
) {
    public DadosDetalhamentoRespostaDTO(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getTopico().getId(), resposta.getData(), new DadosDetalhamentoUsuariosDTO(resposta.getUsuario()));
    }
}
