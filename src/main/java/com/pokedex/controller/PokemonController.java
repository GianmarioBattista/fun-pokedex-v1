package com.pokedex.controller;

import com.pokedex.assembler.PokemonAssembler;
import com.pokedex.command.PokemonCommand;
import com.pokedex.command.PokemonTranslationCommand;
import com.pokedex.model.PokemonResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/pokemon")
@Slf4j
public class PokemonController {

    private final BeanFactory beanFactory;

    private static final Pattern VALID_NAME = Pattern.compile("^[a-zA-Z0-9\\-']{1,30}$");

    public PokemonController(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @GetMapping("/{name}")
    public ResponseEntity<PokemonResponseModel> get(@PathVariable String name) {

        if (!VALID_NAME.matcher(name).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Pokémon name");
        }

        PokemonCommand command = beanFactory.getBean(PokemonCommand.class, name.trim());
        Map<String, Object> commandResponse = command.execute();
        return ResponseEntity.ok().body(PokemonAssembler.toResource(commandResponse));

    }

    @GetMapping("/translated/{name}")
    public ResponseEntity<PokemonResponseModel> getTranslated(@PathVariable String name) {

        if (!VALID_NAME.matcher(name).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Pokémon name");
        }

        PokemonTranslationCommand command = beanFactory.getBean(PokemonTranslationCommand.class, name.trim());
        Map<String, Object> commandResponse = command.execute();
        return ResponseEntity.ok().body(PokemonAssembler.toResource(commandResponse));

    }

}