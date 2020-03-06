package co.simplon.pokedex.service;

import co.simplon.pokedex.dto.TokenDto;
import co.simplon.pokedex.model.Trainer;
import co.simplon.pokedex.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Value("${app.secret-key}")
    private String appSecretKey;

    private TrainerRepository trainerRepository;

    public LoginServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public TokenDto login(String name, String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            String encodedPassword = Base64.getEncoder().encodeToString(messageDigest.digest());

            Optional<Trainer> trainer = trainerRepository.findByNameAndEncodedPassword(name, encodedPassword);

            if (trainer.isPresent()) {
                messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(appSecretKey.getBytes());
                return new TokenDto(trainer.get().getId(), Base64.getEncoder().encodeToString(messageDigest.digest()));
            } else {
                return null;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
