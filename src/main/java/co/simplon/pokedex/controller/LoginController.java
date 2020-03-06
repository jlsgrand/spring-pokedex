package co.simplon.pokedex.controller;

import co.simplon.pokedex.dto.TokenDto;
import co.simplon.pokedex.dto.TrainerDto;
import co.simplon.pokedex.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Login for a trainer.
     * If a trainer name and password match then the login service provides a token to access some controllers.
     *
     * @param trainer the trainer data (name and password)
     * @return a token dto if connection is successful, Unauthorized
     */
    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody TrainerDto trainer) {
        TokenDto token = loginService.login(trainer.getName(), trainer.getPassword());
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.ok(token);
        }
    }

}
