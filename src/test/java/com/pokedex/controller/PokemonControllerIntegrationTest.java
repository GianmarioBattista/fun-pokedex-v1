package com.pokedex.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokedex.client.PokemonClient;
import com.pokedex.client.PokemonTranslationClient;
import com.pokedex.service.PokemonService;
import com.pokedex.service.PokemonTranslationService;
import com.pokedex.utils.VariableUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PokemonControllerIntegrationTest {

    String pokemonNameUrl = "https://pokeapi.co/api/v2/pokemon-species/mewtwo";
    String yodaTranslationUrl = "https://api.funtranslations.com/translate/yoda.json";
    private Map<String, Object> pokemonMockedJson;
    private Map<String, Object> yodaTranslationMockedJson;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private PokemonClient pokemonService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PokemonTranslationClient pokemonTranslationService;

    @BeforeEach
    void setup() throws IOException {

        String pokemonNameMockedResponse = Files.readString(new ClassPathResource("mocks/mewtwo_pokemon_response.json").getFile().toPath());
        pokemonMockedJson = mapper.readValue(pokemonNameMockedResponse, new TypeReference<>() {});

        String yodaTranslation = Files.readString(new ClassPathResource("mocks/yoda_tranlation_response.json").getFile().toPath());
        yodaTranslationMockedJson = mapper.readValue(yodaTranslation, new TypeReference<>() {});

        ReflectionTestUtils.setField(pokemonService, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(pokemonTranslationService, "restTemplate", restTemplate);

    }

    @Test
    void getPokemonControllerApiIntegrationTest() throws Exception {

        ResponseEntity<Map<String, Object>> mockEntity = new ResponseEntity<>(pokemonMockedJson, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                Mockito.eq(pokemonNameUrl),
                Mockito.eq(HttpMethod.GET),
                Mockito.isNull(),
                Mockito.any(ParameterizedTypeReference.class)
        )).thenReturn(mockEntity);

        mockMvc.perform(get("/pokemon/mewtwo").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("mewtwo"))
                .andExpect(jsonPath("$.habitat").value("rare"))
                .andExpect(jsonPath("$.legendary").value(true))
                .andExpect(jsonPath("$.description").exists());

    }

    @Test
    void getTranslatedPokemonControllerIntegrationTest() throws Exception {

        ResponseEntity<Map<String, Object>> mockPokemonEntity = new ResponseEntity<>(pokemonMockedJson, HttpStatus.OK);
        ResponseEntity<Map<String, Object>> mockYodaTranslationEntity = new ResponseEntity<>(yodaTranslationMockedJson, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                Mockito.eq(pokemonNameUrl),
                Mockito.eq(HttpMethod.GET),
                Mockito.isNull(),
                Mockito.any(ParameterizedTypeReference.class)
        )).thenReturn(mockPokemonEntity);

        Mockito.when(restTemplate.exchange(
                Mockito.eq(yodaTranslationUrl),
                Mockito.eq(HttpMethod.POST),
                Mockito.any(HttpEntity.class),
                Mockito.any(ParameterizedTypeReference.class)
        )).thenReturn(mockYodaTranslationEntity);

        mockMvc.perform(get("/pokemon/translated/mewtwo").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("mewtwo"))
                .andExpect(jsonPath("$.habitat").value("rare"))
                .andExpect(jsonPath("$.legendary").value(true))
                .andExpect(jsonPath("$.description").value(VariableUtils.YODA_DESCRIPTION))
                .andReturn();

    }

}
