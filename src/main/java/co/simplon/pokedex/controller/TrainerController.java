package co.simplon.pokedex.controller;

import co.simplon.pokedex.model.Pokemon;
import co.simplon.pokedex.service.TrainerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    /**
     * Gets all trainer pokemons.
     *
     * @param trainerId the trainer ID to look for.
     * @return the list of trainer pokemons.
     */
    @GetMapping("/{trainerId}")
    public List<Pokemon> getTrainerPokemons(@PathVariable Long trainerId) {
        return trainerService.findAllPokemonsBelongingToTrainer(trainerId);
    }
}
