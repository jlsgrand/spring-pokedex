package co.simplon.pokedex;

import co.simplon.pokedex.filter.ApiTrainerFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PokedexApplication {

    @Value("${app.secret-key}")
    private String appSecretKey;

    public static void main(String[] args) {
        SpringApplication.run(PokedexApplication.class, args);
    }

    // Enable CORS for all controllers
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }

    // Add an HTTP filter for /api/trainers/* requests
    @Bean
    public FilterRegistrationBean<ApiTrainerFilter> trainerFilter() {
        FilterRegistrationBean<ApiTrainerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiTrainerFilter(appSecretKey));
        registrationBean.addUrlPatterns("/api/trainers/*");
        return registrationBean;
    }

}
