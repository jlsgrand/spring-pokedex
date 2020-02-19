package co.simplon.pokedex.controller;

import co.simplon.pokedex.model.Pokemon;
import co.simplon.pokedex.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemons")
@CrossOrigin("*")
public class PokemonController {

    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAll();
    }

    @GetMapping("/type/{pokemonType}")
    public ResponseEntity<List<Pokemon>> getPokemonsByTypes(@PathVariable String pokemonType) {
        try {
            return ResponseEntity.ok(pokemonService.getAllByType(pokemonType));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{pokemonNumero}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long pokemonNumero) {
        Optional<Pokemon> pokemon = pokemonService.findOneByNumero(pokemonNumero);

        if (pokemon.isPresent()) {
            return ResponseEntity.ok(pokemon.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pokemon> createNewPokemon(@RequestBody Pokemon newPokemon) {
        return ResponseEntity.ok(pokemonService.savePokemon(newPokemon));
    }

    @PutMapping
    public ResponseEntity<Pokemon> updateNewPokemon(@RequestBody Pokemon pokemonToUpdate) {
        return ResponseEntity.ok(pokemonService.savePokemon(pokemonToUpdate));
    }
}
