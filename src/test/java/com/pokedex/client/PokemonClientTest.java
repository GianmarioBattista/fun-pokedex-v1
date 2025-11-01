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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PokemonClient client;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(client, "restTemplate", restTemplate);
    }

    @Test
    void getPokemonSpecies_shouldReturnResponseBody() {
        Map<String, Object> mockBody = Map.of(VariableUtils.NAME, "pikachu");
        ResponseEntity<Map<String, Object>> mockResponse =
                new ResponseEntity<>(mockBody, HttpStatus.OK);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(mockResponse);

        Map<String, Object> result = client.getPokemonSpecies("pikachu");

        assertNotNull(result);
        assertEquals("pikachu", result.get(VariableUtils.NAME));
    }

    @Test
    void getPokemonSpecies_shouldThrowWhenBodyIsNull() {
        ResponseEntity<Map<String, Object>> mockResponse =
                new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(mockResponse);

        assertThrows(RuntimeException.class, () -> client.getPokemonSpecies("missing"));
    }

    @Test
    void getPokemonSpecies_shouldThrowOnRestTemplateError() {

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        )).thenThrow(new RuntimeException("Network error"));

        assertThrows(RuntimeException.class, () -> client.getPokemonSpecies("charmander"));
    }

}
