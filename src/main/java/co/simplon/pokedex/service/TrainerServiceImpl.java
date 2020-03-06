package co.simplon.pokedex.service;

import co.simplon.pokedex.model.Pokemon;
import co.simplon.pokedex.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private PokemonRepository pokemonRepository;

    public TrainerServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<Pokemon> findAllPokemonsBelongingToTrainer(Long trainerId) {
        return pokemonRepository.findAllByTrainerId(trainerId);
    }
}
