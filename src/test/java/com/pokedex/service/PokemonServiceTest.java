package com.pokedex.service;

import com.pokedex.client.PokemonClient;
import com.pokedex.utils.PokemonTestUtils;
import com.pokedex.utils.VariableUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private PokemonClient pokemonClient;

    @InjectMocks
    private PokemonService pokemonService;

    @Test
    void getPokemonInfo_returnsOk() {

        when(pokemonClient.getPokemonSpecies(anyString())).thenReturn(PokemonTestUtils.createPokemonConnectorDummyResponse());

        Map<String, Object> result = pokemonService.getPokemonInfo("mewtwo");


        assertNotNull(result);
        assertEquals("mewtwo", result.get(VariableUtils.NAME));
        assertEquals(true, result.get(VariableUtils.IS_LEGENDARY));
        assertTrue(result.containsKey(VariableUtils.DESCRIPTION));
        assertTrue(result.containsKey(VariableUtils.HABITAT));
    }

    @Test
    void getPokemonInfo_shouldThrowException_whenResponseBodyIsNull() {

        when(pokemonClient.getPokemonSpecies(anyString())).thenReturn(null);

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> pokemonService.getPokemonInfo("mewtwo")
        );

        assertEquals("[PokemonService] Error while processing Pokemon API response", thrown.getMessage());
    }

    @Test
    void getPokemonInfo_shouldThrowException_whenRestTemplateThrowsError() {

        when(pokemonClient.getPokemonSpecies(anyString())).thenThrow(new RuntimeException("Connection failed"));


        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> pokemonService.getPokemonInfo("mewtwo")
        );

        assertTrue(thrown.getMessage().contains("[PokemonService] Error while processing Pokemon API response"));
    }
}
