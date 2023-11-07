package com.kenzie.threadsafety.icecream.model;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * A flavor of ice cream.
 */
public enum Flavor {
    VANILLA("Vanilla"),
    CHOCOLATE("Chocolate"),
    STRAWBERRY("Strawberry");

    private static final Map<String, Flavor> FLAVOR_LOOKUP = ImmutableMap.of(
        "Vanilla", VANILLA,
        "Chocolate", CHOCOLATE,
        "Strawberry", STRAWBERRY
    );

    private String name;

    /**
     * Creates a Flavor with the given name.
     * @param name The flavor's name
     */
    Flavor(String name) {
        this.name = name;
    }

    /**
     * Returns Flavor for the given flavor name, if found. If flavor not found, returns null.
     * @param name The flavor name to return Flavor object for
     * @return The Flavor corresponding to the flavor name, if found; otherwise, returns null
     */
    public static Flavor byName(String name) {
        return FLAVOR_LOOKUP.get(name);
    }

    public String getCommonName() {
        return name;
    }
}
