package br.com.alura.forumhub.domain.topico.DTO;

import br.com.alura.forumhub.domain.curso.DTO.DadosDetalhamentoCursoDTO;
import br.com.alura.forumhub.domain.resposta.DTO.DadosDetalhamentoRespostaDTO;
import br.com.alura.forumhub.domain.topico.Topico;
import br.com.alura.forumhub.domain.topico.TopicoStatus;
import br.com.alura.forumhub.domain.usuario.DTO.DadosDetalhamentoUsuariosDTO;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoTopicoDTO(
        Long id,
        String titulo,
        String mensagem,
        OffsetDateTime data,
        TopicoStatus status,
        DadosDetalhamentoUsuariosDTO autor,
        DadosDetalhamentoCursoDTO curso,
        List<DadosDetalhamentoRespostaDTO> respostas
) {
    public DadosDetalhamentoTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData(), topico.getStatus(), new DadosDetalhamentoUsuariosDTO(topico.getUsuario()), new DadosDetalhamentoCursoDTO(topico.getCurso()),
                topico.getRespostas().stream()
                        .map(dr -> new DadosDetalhamentoRespostaDTO(dr.getId(), dr.getMensagem(), dr.getTopico().getId(),
                                dr.getData(), new DadosDetalhamentoUsuariosDTO(dr.getUsuario()))).collect(Collectors.toList()));
    }
}
