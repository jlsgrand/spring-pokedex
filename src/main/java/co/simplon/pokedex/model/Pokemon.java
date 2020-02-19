package co.simplon.pokedex.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_seq_id")
    @SequenceGenerator(name = "pokemon_seq_id", sequenceName = "pokemon_seq_id", allocationSize = 1)
    private Long numero;

    private String photoUrl;

    @NotNull
    @Column(nullable = false)
    private String nom;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Type> types;

    public Long getNumero() {
        return numero;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getNom() {
        return nom;
    }

    public List<Type> getTypes() {
        return types;
    }
}
