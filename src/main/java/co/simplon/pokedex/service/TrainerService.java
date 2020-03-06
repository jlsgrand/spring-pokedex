package co.simplon.pokedex.service;

import co.simplon.pokedex.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainerService {
    /**
     * Get all the pokemons of a trainer
     *
     * @param trainerId the trainer ID to check
     * @return the list of trainer pokemons.
     */
    List<Pokemon> findAllPokemonsBelongingToTrainer(Long trainerId);
}
