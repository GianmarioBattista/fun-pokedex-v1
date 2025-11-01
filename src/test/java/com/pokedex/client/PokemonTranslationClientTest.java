package com.pokedex.client;

import com.pokedex.utils.VariableUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.ValueRange;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonTranslationClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PokemonTranslationClient client;


    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(client, "restTemplate", restTemplate);
    }

    @Test
    void translateText_shouldReturnTranslatedText() {

        Map<String, Object> mockResponse = Map.of(
                VariableUtils.CONTENTS, Map.of(VariableUtils.TRANSLATED, VariableUtils.YODA_DESCRIPTION)
        );

        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class)
        )).thenReturn(response);

        String result = client.translateText(VariableUtils.POKEMON_DESCRIPTION, VariableUtils.YODA);

        assertEquals(VariableUtils.YODA_DESCRIPTION, result);
    }

    @Test
    void translateText_shouldReturnOriginalTextOnError() {

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class)
        )).thenThrow(new RuntimeException("API down"));

        String input = VariableUtils.POKEMON_DESCRIPTION;
        String result = client.translateText(input, VariableUtils.YODA);

        assertEquals(input, result);
    }
}
