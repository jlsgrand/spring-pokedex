package co.simplon.pokedex.repository;

import co.simplon.pokedex.model.Pokemon;
import co.simplon.pokedex.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findAllByTypes(Type type);

    @Query("select p from Pokemon p join fetch p.trainerSet t where t.id = :trainerId")
    List<Pokemon> findAllByTrainerId(@Param("trainerId") Long trainerId);
}
