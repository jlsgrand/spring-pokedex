package co.simplon.pokedex.controller;

import co.simplon.pokedex.model.Type;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon-types")
public class PokemonTypesController {

    /**
     * Get all pokemons types.
     *
     * @return the list of pokemon types.
     */
    @GetMapping
    public List<Type> getAllTypes() {
        return List.of(Type.values());
    }
}
