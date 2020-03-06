package co.simplon.pokedex.service;

import co.simplon.pokedex.model.Pokemon;
import co.simplon.pokedex.model.Type;
import co.simplon.pokedex.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<Pokemon> getAll() {
        return pokemonRepository.findAll();
    }

    @Override
    public List<Pokemon> getAllByType(String type) throws IllegalArgumentException {
        return pokemonRepository.findAllByTypes(Type.valueOf(type));
    }

    @Override
    public Optional<Pokemon> findOneByNumber(Long pokemonNumber) {
        return pokemonRepository.findById(pokemonNumber);
    }

    @Override
    public Pokemon savePokemon(Pokemon pokemonToSave) {
        return pokemonRepository.save(pokemonToSave);
    }
}
