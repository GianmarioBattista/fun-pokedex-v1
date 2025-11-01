package com.pokedex.service;

import com.pokedex.client.PokemonClient;
import com.pokedex.utils.PokemonUtils;
import com.pokedex.utils.VariableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PokemonService {

    private final PokemonClient pokemonClient;

    public PokemonService(@Autowired PokemonClient pokemonClient) {
        this.pokemonClient = pokemonClient;
    }

    public Map<String, Object> getPokemonInfo(String name) {

        try {

            Map<String, Object> response = pokemonClient.getPokemonSpecies(name);

            if (response == null) {
                throw new RuntimeException("[PokemonService] Pokemon API response is null");
            }

            String description = PokemonUtils.extractDescription(response);
            String habitat = PokemonUtils.extractHabitat(response);
            boolean isLegendary = (boolean) response.get("is_legendary");

            return PokemonUtils.createPokemonMap(name, description, habitat, isLegendary);

        } catch (Exception e) {
            throw new RuntimeException("[PokemonService] Error while processing Pokemon API response", e);
        }
    }

}
