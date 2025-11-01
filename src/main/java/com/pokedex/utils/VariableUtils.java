package com.pokedex.utils;

public class VariableUtils {

    // Internal model map key definition
    public final static String NAME = "name";
    public final static String DESCRIPTION = "description";
    public final static String HABITAT = "habitat";
    public final static String IS_LEGENDARY = "isLegendary";

    // Pokemon rest connector map key definition
    public final static String RESPONSE_NAME = "name";
    public final static String FLAVOR_TEXT_ENTRIES = "flavor_text_entries";
    public final static String RESPONSE_HABITAT = "habitat";
    public final static String RESPONSE_IS_LEGENDARY = "is_legendary";

    // Translation rest connector map key definition
    public final static String CONTENTS = "contents";
    public final static String TRANSLATED = "translated";


    // FLAVOR_TEXT_ENTRIES sub-keys
    public final static String FLAVOR_TEXT = "flavor_text";
    public final static String LANGUAGE = "language";

    // RESPONSE_HABITAT sub-keys
    public final static String HABITAT_NAME = "name";

    // LANGUAGE sub-keys
    public final static String LANGUAGE_NAME = "name";

    // Languages
    public final static String ENGLISH = "en";
    public final static String ITALIAN = "it";

    // Habitat types
    public final static String CAVE = "cave";
    public final static String RARE = "rare";

    // Translation types
    public static final String YODA = "yoda";
    public static final String SHAKESPEARE = "shakespeare";

    // Descriptions
    public static final String POKEMON_DESCRIPTION = "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.";
    public static final String YODA_DESCRIPTION = "Created by a scientist after years of horrific gene splicing and dna engineering experiments,  it was.";

}
