package com.pokedex.model;

public class PokemonResponseModel {

    private String name;
    private String description;
    private String habitat;
    private Boolean isLegendary;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHabitat() {
        return habitat;
    }

    public Boolean getLegendary() {
        return isLegendary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public void setIsLegendary(Boolean isLegendary) {
        this.isLegendary = isLegendary;
    }
}

