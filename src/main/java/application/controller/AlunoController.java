package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Aluno;
import application.record.AlunoDTO;
import application.record.AlunoInsertDTO;
import application.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepo;

    @PostMapping
    public AlunoDTO insert(@RequestBody AlunoInsertDTO novoAluno) {
        return new AlunoDTO(alunoRepo.save(new Aluno(novoAluno)));
    }

    @GetMapping(("/{id}"))
    public AlunoDTO getOne(@PathVariable long id){
        Optional<Aluno> resultado = alunoRepo.findById(id); 

        if (resultado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }
        return new AlunoDTO(resultado.get());
    }

    @GetMapping
    public Iterable<AlunoDTO> getAll() {
        return alunoRepo.findAll().stream().map(AlunoDTO::new).toList();
    }

    @PutMapping("/{id}")
    public AlunoDTO update(@PathVariable long id, @RequestBody AlunoInsertDTO novosDados) {
        Optional<Aluno> resultado = alunoRepo.findById(id);
        
        if(resultado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }
        
        Aluno alunoExistente = resultado.get();

         if (novosDados.nome() != null) {
            alunoExistente.setNome(novosDados.nome());
        }
        
         if (novosDados.idade() != null) {
            alunoExistente.setIdade(novosDados.idade());
         }
        return new AlunoDTO(alunoRepo.save(alunoExistente));
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        if(!alunoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }
        
        alunoRepo.deleteById(id);

    }
}
