package com.pokedex.assembler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokedex.model.PokemonResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PokemonAssembler {

    private static final Logger log = LoggerFactory.getLogger(PokemonAssembler.class);

    public static PokemonResponseModel toResource(Map<String, Object> input) {

        ObjectMapper mapper = new ObjectMapper();
        PokemonResponseModel response = null;

        try {
            response = mapper.convertValue(input, PokemonResponseModel.class);
            log.info("[PokemonResponseModel] converted map into response resource: {}", response.toString());

        } catch (Exception e) {
            throw new RuntimeException("[PokemonResponseModel] error while converting input map to response resource");
        }

        return response;
    }

}
