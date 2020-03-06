package co.simplon.pokedex.repository;

import co.simplon.pokedex.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByNameAndEncodedPassword(String name, String encodedPassword);
}
