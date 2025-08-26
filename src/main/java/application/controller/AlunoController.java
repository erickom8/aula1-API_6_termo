package application.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.record.AlunoDTO;
import application.record.AlunoInsertDTO;
import application.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public AlunoDTO insert(@RequestBody AlunoInsertDTO novoAluno) {
        return alunoService.insert(novoAluno);
    }

    @GetMapping(("/{id}"))
    public AlunoDTO getOne(@PathVariable long id){
        return alunoService.getOne(id);
    }

    @GetMapping
    public Iterable<AlunoDTO> getAll() {
        return alunoService.getAll();
    }

    @PutMapping("/{id}")
    public AlunoDTO update(@PathVariable long id, @RequestBody AlunoInsertDTO novosDados) {
        return alunoService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        alunoService.delete(id);

    }
}
