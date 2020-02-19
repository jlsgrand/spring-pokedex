package co.simplon.pokedex.service;

import co.simplon.pokedex.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PokemonService {

    List<Pokemon> getAll();

    List<Pokemon> getAllByType(String Type) throws IllegalArgumentException;

    Optional<Pokemon> findOneByNumero(Long pokemonNumero);

    Pokemon savePokemon(Pokemon pokemonToSave);

}
