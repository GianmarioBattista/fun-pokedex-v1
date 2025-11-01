package com.pokedex.service;

import com.pokedex.client.PokemonClient;
import com.pokedex.utils.PokemonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PokemonService {

    private final PokemonClient pokemonClient;

    public PokemonService(@Autowired PokemonClient pokemonClient) {
        this.pokemonClient = pokemonClient;
    }

    public Map<String, Object> getPokemonInfo(String name) {

        Map<String, Object> response = pokemonClient.getPokemonSpecies(name);

        if (response == null) {
            throw new RuntimeException("[PokemonService] Pokemon API response is null");
        }

        String description = PokemonUtils.extractDescription(response);
        String habitat = PokemonUtils.extractHabitat(response);
        boolean isLegendary = (boolean) response.get("is_legendary");

        return PokemonUtils.createPokemonMap(name, description, habitat, isLegendary);
    }

}
