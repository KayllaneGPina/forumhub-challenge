package br.com.alura.forumhub.domain.curso;

import br.com.alura.forumhub.domain.curso.DTO.DadosCadastroCursoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso (DadosCadastroCursoDTO dados){
        this.categoria = dados.categoria();
        this.nome = dados.nome();
    }
}
