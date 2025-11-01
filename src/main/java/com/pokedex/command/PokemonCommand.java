package com.pokedex.command;

import com.pokedex.service.PokemonService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE) // it creates a new object instance each time it get called
public class PokemonCommand extends BaseCommand<Map<String, Object>>{

    private final String pokemonName;

    @Autowired
    private PokemonService pokemonService;

    public PokemonCommand(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public boolean canExecute() {
        return !StringUtils.isBlank(pokemonName);
    }

    public Map<String, Object> doExecute() {
        return pokemonService.getPokemonInfo(pokemonName);
    }

}
