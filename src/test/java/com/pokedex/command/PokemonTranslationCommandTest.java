package com.pokedex.command;

import com.pokedex.service.PokemonService;
import com.pokedex.service.PokemonTranslationService;
import com.pokedex.utils.PokemonTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PokemonTranslationCommandTest {

    @Mock
    private PokemonService pokemonService;

    @Mock
    private PokemonTranslationService translationService;

    @InjectMocks
    private PokemonTranslationCommand command = new PokemonTranslationCommand("mewtwo");

    @BeforeEach
    void setUp() {

    }

    @Test
    void canExecute_shouldReturnTrue_whenNameIsNotBlank() {
        assertTrue(command.canExecute());
    }

    @Test
    void doExecute_shouldReturnTranslatedPokemonInfo() {

        Map<String, Object> pokemonResponse = PokemonTestUtils.createPokemonConnectorDummyResponse();
        Map<String, Object> translatedResponse = PokemonTestUtils.createPokemonTranslationServiceDummyResponse();

        Mockito.when(pokemonService.getPokemonInfo("mewtwo"))
                .thenReturn(pokemonResponse);
        Mockito.when(translationService.translatePokemonInfo(pokemonResponse))
                .thenReturn(translatedResponse);

        Map<String, Object> result = command.doExecute();

        assertEquals(translatedResponse, result);
        Mockito.verify(pokemonService).getPokemonInfo("mewtwo");
        Mockito.verify(translationService).translatePokemonInfo(pokemonResponse);

    }
}