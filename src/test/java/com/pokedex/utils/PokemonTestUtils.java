package com.pokedex.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pokedex.utils.VariableUtils.*;

public class PokemonTestUtils {

    public static Map<String, Object> createPokemonConnectorDummyResponse() {

        Map<String, Object> habitatMap = new HashMap<>();
        habitatMap.put(HABITAT_NAME, RARE);

        Map<String, Object> map = new HashMap<>();
        map.put(RESPONSE_NAME, "mewtwo");
        map.put(FLAVOR_TEXT_ENTRIES,
                List.of(Map.of(FLAVOR_TEXT, POKEMON_DESCRIPTION,
                                LANGUAGE, Map.of(LANGUAGE_NAME, ENGLISH)),
                        Map.of(FLAVOR_TEXT, "E' stato creato in laboratorio",
                                LANGUAGE, Map.of(LANGUAGE_NAME, ITALIAN))));
        map.put(RESPONSE_HABITAT, habitatMap);
        map.put(RESPONSE_IS_LEGENDARY, true);
        return map;

    }

    public static Map<String, Object> createPokemonServiceDummyResponse() {

        return PokemonUtils.createPokemonMap("mewtwo",
                POKEMON_DESCRIPTION,
                RARE,
                true
        );

    }

    public static Map<String, Object> createPokemonTranslationServiceDummyResponse() {

        Map<String, Object> map = new HashMap<>();
        map.put(RESPONSE_NAME, "mewtwo");
        map.put(FLAVOR_TEXT_ENTRIES,
                List.of(Map.of(FLAVOR_TEXT, "Created by a scientist after years of horrific\fgene splicing and DNA engineering experiments, it was",
                                LANGUAGE, Map.of(LANGUAGE_NAME, ENGLISH)),
                        Map.of(FLAVOR_TEXT, "Creato in laboratorio",
                                LANGUAGE, Map.of(LANGUAGE_NAME, ITALIAN))));
        map.put(RESPONSE_HABITAT, Map.of(LANGUAGE_NAME, RARE));
        map.put(RESPONSE_IS_LEGENDARY, true);

        return map;

    }

    public static Map<String, Object> createPokemonDummyItNoHabitatConnectorResponse() {

        Map<String, Object> map = new HashMap<>();
        map.put(RESPONSE_NAME, "mewtwo");
        map.put(FLAVOR_TEXT_ENTRIES,
                List.of(Map.of(FLAVOR_TEXT, "E' stato creato in laboratorio",
                        LANGUAGE, Map.of(HABITAT_NAME, ITALIAN))));
        map.put(RESPONSE_IS_LEGENDARY, true);
        return map;

    }

    public static Map<String, Object> createDummyTranslationClientResponse() {

        Map<String, Object> total = Map.of("total", 1);
        Map<String, Object> contentsMap = Map.of("translated", YODA_DESCRIPTION,
                                                 "text", POKEMON_DESCRIPTION,
                                                 "translation", "yoda");
        return Map.of("total", total, "contents", contentsMap);

    }

}
