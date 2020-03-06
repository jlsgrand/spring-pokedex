package co.simplon.pokedex.controller;

import co.simplon.pokedex.model.Pokemon;
import co.simplon.pokedex.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    /**
     * Get all pokemons.
     *
     * @return the list of all pokemons from DB.
     */
    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAll();
    }

    /**
     * Get all pokemons based on a pokemon type.
     *
     * @param pokemonType the pokemon type filtering criteria.
     * @return a list of pokemons matching the filtering criteria.
     */
    @GetMapping("/type/{pokemonType}")
    public ResponseEntity<List<Pokemon>> getPokemonsByTypes(@PathVariable String pokemonType) {
        try {
            return ResponseEntity.ok(pokemonService.getAllByType(pokemonType));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get a pokemon with its number.
     *
     * @param pokemonNumber the pokemon number.
     * @return the pokemon if found, not found response code otherwise.
     */
    @GetMapping("{pokemonNumber}")
    public ResponseEntity<Pokemon> getPokemonByNumber(@PathVariable Long pokemonNumber) {
        Optional<Pokemon> pokemon = pokemonService.findOneByNumber(pokemonNumber);

        if (pokemon.isPresent()) {
            return ResponseEntity.ok(pokemon.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new pokemon.
     *
     * @param newPokemon the new pokemon to create.
     * @return the created pokemon.
     */
    @PostMapping
    public ResponseEntity<Pokemon> createNewPokemon(@RequestBody Pokemon newPokemon) {
        return ResponseEntity.ok(pokemonService.savePokemon(newPokemon));
    }

    /**
     * Updates an existing pokemon.
     *
     * @param pokemonToUpdate the pokemon to update.
     * @return the updated pokemon.
     */
    @PutMapping
    public ResponseEntity<Pokemon> updateNewPokemon(@RequestBody Pokemon pokemonToUpdate) {
        return ResponseEntity.ok(pokemonService.savePokemon(pokemonToUpdate));
    }
}
