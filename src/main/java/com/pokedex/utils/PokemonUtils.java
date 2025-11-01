package com.pokedex.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class PokemonUtils {

    public static String extractDescription(Map<String, Object> response) {
        var flavorTexts = (Iterable<Map<String, Object>>) response.get("flavor_text_entries");
        if (flavorTexts == null) return "No description available";

        for (Map<String, Object> entry : flavorTexts) {
            Map<String, Object> language = (Map<String, Object>) entry.get("language");
            if ("en".equals(language.get("name"))) {
                return ((String) entry.get("flavor_text"))
                        .replace("\n", " ")
                        .replace("\f", " ");
            }
        }
        return "No English description found";
    }

    public static String extractHabitat(Map<String, Object> response) {
        Map<String, Object> habitat = (Map<String, Object>) response.get("habitat");
        return habitat != null ? (String) habitat.get("name") : "unknown";
    }

    public static Map<String, Object> createPokemonMap(String name, String description, String habitat, Boolean isLegendary) {

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("description", description);
        map.put("habitat", habitat);
        map.put("isLegendary", isLegendary);

        return map;

    }

}
