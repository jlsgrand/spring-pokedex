package co.simplon.pokedex.filter;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ApiTrainerFilter extends OncePerRequestFilter {
    private String appSecretKey;

    public ApiTrainerFilter(String appSecretKey) {
        this.appSecretKey = appSecretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // If HttpMethod is OPTIONS, it's a preflight check, so ignore it.
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
            super.doFilter(request, response, chain);
            return;
        }

        // Else, try to get the X-Authorization header and get the token.
        try {
            String token = request.getHeader("X-Authorization");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(appSecretKey.getBytes());
            String correctToken = Base64.getEncoder().encodeToString(messageDigest.digest());

            // If token is not present or not valid, user should not have access
            if (token == null || token.isEmpty() || !token.equals(correctToken)) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized User");
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Token Hashing Problem");
            return;
        }

        super.doFilter(request, response, chain);
    }
}
