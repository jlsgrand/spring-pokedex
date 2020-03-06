package co.simplon.pokedex.service;

import co.simplon.pokedex.dto.TokenDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    TokenDto login(String name, String password);
}
