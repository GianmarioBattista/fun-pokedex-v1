package com.pokedex.client;

import com.pokedex.utils.VariableUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PokemonTranslationClient {

    private static final Logger log = LoggerFactory.getLogger(PokemonTranslationClient.class);

    private static final String YODA_API = "https://api.funtranslations.com/translate/yoda.json";
    private static final String SHAKESPEARE_API = "https://api.funtranslations.com/translate/shakespeare.json";

    private final RestTemplate restTemplate = new RestTemplate();

    public String translateText(String text, String type) {

        String url = VariableUtils.YODA.equalsIgnoreCase(type) ? YODA_API : SHAKESPEARE_API;

        try {
            Map<String, String> requestBody = Map.of("text", text);

            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(requestBody),
                    new ParameterizedTypeReference<>() {}
            );

            Map<String, Object> body = response.getBody();

            if (body != null && body.containsKey(VariableUtils.CONTENTS)) {
                Map<String, Object> contents = (Map<String, Object>) body.get(VariableUtils.CONTENTS);
                return (String) contents.get(VariableUtils.TRANSLATED);
            }

        } catch (Exception e) {
            log.warn("[PokemonTranslationClient] Failed to call translation API: {}", e.getMessage());
        }

        // fallback that returns original description
        return text;
    }
}
