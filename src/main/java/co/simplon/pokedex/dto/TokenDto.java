package co.simplon.pokedex.dto;

public class TokenDto {

    private Long trainerId;
    private String token;

    public TokenDto(Long trainerId, String token) {
        this.trainerId = trainerId;
        this.token = token;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public String getToken() {
        return token;
    }
}
