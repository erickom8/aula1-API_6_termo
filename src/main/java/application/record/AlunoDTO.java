package application.record;

import application.model.Aluno;

public record AlunoDTO(long id, String nome, int idade) {
    public AlunoDTO(Aluno dados) {
        this(dados.getId(), dados.getNome(), dados.getIdade());
    }
}
