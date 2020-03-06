package co.simplon.pokedex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_seq_id")
    @SequenceGenerator(name = "pokemon_seq_id", sequenceName = "pokemon_seq_id", allocationSize = 1)
    private Long number;

    private String photoUrl;

    @NotNull
    @Column(nullable = false)
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Type> types;

    @JsonIgnore
    @ManyToMany(mappedBy = "pokemonSet")
    private Set<Trainer> trainerSet = new HashSet<>();

    public Long getNumber() {
        return number;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getName() {
        return name;
    }

    public List<Type> getTypes() {
        return types;
    }
}
