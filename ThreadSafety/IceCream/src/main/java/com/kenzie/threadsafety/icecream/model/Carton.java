package com.kenzie.threadsafety.icecream.model;

/**
 * A carton of ice cream.
 */
public class Carton {
    private final Flavor flavor;
    private int numScoops;

    private Carton(Flavor flavor, boolean isEmpty) {
        this.flavor = flavor;
        numScoops = isEmpty ? 0 : 10;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public boolean isEmpty() {
        return numScoops == 0;
    }

    public static Carton makeCarton(Flavor flavor) {
        return new Carton(flavor, false);
    }

    public static Carton makeEmptyCarton(Flavor flavor) {
        return new Carton(flavor, true);
    }

    @Override
    public String toString() {
        return "Carton{" +
            "flavor=" + flavor +
            ", isEmpty=" + isEmpty() +
            ", numScoops=" + numScoops +
            '}';
    }
}
