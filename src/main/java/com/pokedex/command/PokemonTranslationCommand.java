package com.pokedex.command;

import com.pokedex.service.PokemonService;
import com.pokedex.service.PokemonTranslationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class PokemonTranslationCommand extends BaseCommand<Map<String, Object>> {

    private final String pokemonName;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonTranslationService translationService;

    public PokemonTranslationCommand(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    @Override
    public boolean canExecute() {
        return !StringUtils.isBlank(pokemonName);
    }

    @Override
    public Map<String, Object> doExecute() {

        Map<String, Object> baseInfo = pokemonService.getPokemonInfo(pokemonName);
        return translationService.translatePokemonInfo(baseInfo);

    }
}
