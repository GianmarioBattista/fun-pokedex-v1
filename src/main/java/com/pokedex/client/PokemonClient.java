package com.pokedex.client;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class PokemonClient {

    private static final Logger log = LoggerFactory.getLogger(PokemonClient.class);
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon-species/";

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getPokemonSpecies(String name) {

        String url = BASE_URL + name;

        try {
            ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (responseEntity.getBody() == null) {
                log.error("[PokemonClient] Could not get pokemon species data from response body");
                throw new Exception();
            }

            return responseEntity.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Error calling PokeAPI", e);
        }
    }
}
