package com.pokedex.service;

import com.pokedex.client.PokemonTranslationClient;
import com.pokedex.utils.PokemonTestUtils;
import com.pokedex.utils.VariableUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonTranslationServiceTest {

    @Mock
    private PokemonTranslationClient client;

    @InjectMocks
    private PokemonTranslationService pokemonTranslationService;


    @Test
    void translatePokemonInfo() {

        when(client.translateText(anyString(), anyString())).thenReturn(VariableUtils.YODA_DESCRIPTION);

        Map<String, Object> serviceResponse = pokemonTranslationService.translatePokemonInfo(PokemonTestUtils.createPokemonServiceDummyResponse());

        assertNotNull(serviceResponse);
        assertEquals(VariableUtils.YODA_DESCRIPTION, serviceResponse.get(VariableUtils.DESCRIPTION));
    }
}