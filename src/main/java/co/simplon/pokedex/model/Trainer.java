package co.simplon.pokedex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainer_seq_id")
    @SequenceGenerator(name = "trainer_seq_id", sequenceName = "trainer_seq_id", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    private String encodedPassword;

    @ManyToMany
    @JoinTable(
            name = "trainer_pokemon",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id"))
    private Set<Pokemon> pokemonSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public Set<Pokemon> getPokemonSet() {
        return pokemonSet;
    }
}
