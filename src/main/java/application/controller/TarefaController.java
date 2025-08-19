package application.controller;

import java.util.List;
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

import application.model.Tarefa;
import application.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepo;

    @PostMapping
    public Tarefa insert(@RequestBody Tarefa novaTarefa) {
        return tarefaRepo.save(novaTarefa);
    }

    @GetMapping(("/{id}"))
    public Tarefa getOne(@PathVariable long id){
        Optional<Tarefa> resultado = tarefaRepo.findById(id); 

        if (resultado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada"
            );
        }
        return resultado.get();
    }

    @GetMapping
    public List<Tarefa> getAll() {
        return tarefaRepo.findAll();
    }

    @PutMapping("/{id}")
    public Tarefa update(@PathVariable long id, @RequestBody Tarefa novosDados) {
        Optional<Tarefa> resultado = tarefaRepo.findById(id);
        
        if(resultado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada"
            );
        }
        
        resultado.get().setDescricao(novosDados.getDescricao());
        return tarefaRepo.save(resultado.get());
        

    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        if(!tarefaRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada"
            );
        }
        
        tarefaRepo.deleteById(id);

    }


}
