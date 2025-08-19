package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    
}
