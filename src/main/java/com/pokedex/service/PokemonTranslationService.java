package com.pokedex.service;

import com.pokedex.client.PokemonTranslationClient;
import com.pokedex.utils.VariableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PokemonTranslationService {

    private final PokemonTranslationClient translationClient;

    private static final Logger log = LoggerFactory.getLogger(PokemonTranslationService.class);

    public PokemonTranslationService(@Autowired PokemonTranslationClient translationClient) {
        this.translationClient = translationClient;
    }

    public Map<String, Object> translatePokemonInfo(Map<String, Object> baseInfo) {

        String description = (String) baseInfo.get(VariableUtils.DESCRIPTION);
        String habitat = (String) baseInfo.get(VariableUtils.HABITAT);
        boolean isLegendary = (boolean) baseInfo.get(VariableUtils.IS_LEGENDARY);

        String translationType = (isLegendary || VariableUtils.CAVE.equalsIgnoreCase(habitat))
                ? VariableUtils.YODA
                : VariableUtils.SHAKESPEARE;

        log.info("[PokemonTranslationService] Translating description: {}, with type: {}", translationType, translationType);
        String translated = translationClient.translateText(description, translationType);
        log.info("[PokemonTranslationService] Translation: {}", translated);

        baseInfo.replace(VariableUtils.DESCRIPTION, translated);
        return baseInfo;
    }

}
