package com.pokedex.command;

import com.pokedex.service.PokemonService;
import com.pokedex.utils.PokemonTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PokemonCommandTest {

    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonCommand pokemonCommand = new PokemonCommand("mewtwo");

    @Test
    void canExecute_shouldReturnTrue_whenNameIsNotBlank() {
        assertTrue(pokemonCommand.canExecute());
    }

    @Test
    void canExecute_shouldReturnFalse_whenNameIsBlank() {
        PokemonCommand command = new PokemonCommand(" ");
        assertFalse(command.canExecute());
    }

    @Test
    void doExecute_shouldCallPokemonServiceAndReturnResult() {

        Map<String, Object> expected = PokemonTestUtils.createPokemonConnectorDummyResponse();

        Mockito.when(pokemonService.getPokemonInfo("mewtwo"))
                .thenReturn(expected);

        Map<String, Object> result = pokemonCommand.doExecute();

        assertEquals(expected, result);
        Mockito.verify(pokemonService).getPokemonInfo("mewtwo");

    }


}