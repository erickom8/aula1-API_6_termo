package application.model;

import application.record.AlunoDTO;
import application.record.AlunoInsertDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private Integer idade;

    public Aluno(AlunoDTO dados) {
        this.setId(dados.id());
        this.setNome(dados.nome());
        this.setIdade(dados.idade());
    }

    public Aluno(AlunoInsertDTO dados) {
        this.setNome(dados.nome());
        this.setIdade(dados.idade());
    }
}