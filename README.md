# Pokedex Spring REST API

Ce projet a pour but de résumer quelques notions de base en Spring boot avec un exemple de pokedex.

## Les controllers

Les différents controllers gèrent les requêtes HTTP entrantes sur des URL bien définies.

Par exemple, le controller [PokemonController](./src/main/java/co/simplon/pokedex/controller/PokemonController.java) permet de récupérer la liste de tous les pokemons du Pokédex, un pokémon avec son numéro, ...

## Les services

Les services sont responsables de la logique métier de l'application. Bien souvent, pour des applications simples comme celle-ci les services ne font que des appels aux repositories.

Ici un des services a un peu de travail : le [LoginService](./src/main/java/co/simplon/pokedex/service/LoginServiceImpl.java). Il est responsable de vérifier que le mot de passe envoyé par l'utilisateur correspond au hash du mot de passe que l'on a stocké en base de données.

## Les repositories

Les repositories sont magiques pour aller nous récupérer les données en base de données avec peu d'effort.

Bien souvent il nous suffit d'étendre l'interface magique `JpaRepository` pour avoir accès aux fonctions de base du type `save()`, `findAll()`, ...

On peut ajouter des méthodes et gérer nous même la requête qui va être éxécutée en utilisant l'annotation `@Query`

```java
@Query("select p from Pokemon p join fetch p.trainerSet t where t.id = :trainerId")
List<Pokemon> findAllByTrainerId(@Param("trainerId") Long trainerId);
```

On peut aussi laisser Spring Data deviner la requête à éxécuter avec le nom des fonctions si on respecte quelques règles de nommage --> [cf Documentation officielle Spring Data](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation).

## Un filtre HTTP

Afin d'illustrer ce que l'on peut faire avec les requêtes HTTP, nous avons rajouté ici un filtre sur les requêtes HTTP (seulement sur `/api/trainers`).

Il s'agit du filtre [ApiTrainerFilter](./src/main/java/co/simplon/pokedex/filter/ApiTrainerFilter.java).

Ce filtre doit être déclaré dans la configuration de notre application. C'est pourquoi elle apparaît dans la classe [PokedexApplication](./src/main/java/co/simplon/pokedex/PokedexApplication.java).

```java
@Bean
public FilterRegistrationBean<ApiTrainerFilter> trainerFilter() {
    FilterRegistrationBean<ApiTrainerFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new ApiTrainerFilter(appSecretKey));
    registrationBean.addUrlPatterns("/api/trainers/*");
    return registrationBean;
}
```

Ce filtre va vérifier pour certaines requêtes un header `X-Authorization` est ajouté et que ce header contient un token valide pour se connecter à l'application.

N.B. **Attention, la méthode employée ici n'est pas sécurisée car elle vérifie le même token pour tous les utilisateurs**. Cet exemple est juste utilisé pour illustrer ce que l'on peut faire avec un filtre. Pour sécuriser son API, il faut **mettre en place Spring Security** ! 

## Une configuration globale du CORS

Pour autoriser les requêtes venant du front en **mode développement** une configuration supplémentaire est ajoutée dans la classe principale de notre application ([PokedexApplication](./src/main/java/co/simplon/pokedex/PokedexApplication.java)).

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        }
    };
}
```

Avec cette configuration pas besoin d'ajouter l'annotation `@CrossOrigin` à tous les controllers.
