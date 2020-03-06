package co.simplon.pokedex.dto;

public class TrainerDto {
    private String name;
    private String password;

    public TrainerDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
