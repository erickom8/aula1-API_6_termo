package application.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class IndexController{
    // private List<String> frutas = Arrays.asList("Maça", "Melão", "Maracujá");
    
    private List<String> frutas = new ArrayList<String>();

    @GetMapping
    public List<String> home() {
        return frutas;
    }

    @PostMapping
    public List<String> insert(@RequestBody String fruta) {
        frutas.add(fruta);
        return frutas;
    }
}