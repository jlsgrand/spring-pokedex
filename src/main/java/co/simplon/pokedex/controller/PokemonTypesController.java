package co.simplon.pokedex.controller;

import co.simplon.pokedex.model.Type;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon-types")
@CrossOrigin("*")
public class PokemonTypesController {
    @GetMapping
    public List<Type> getAllTypes() {
        return List.of(Type.values());
    }
}
