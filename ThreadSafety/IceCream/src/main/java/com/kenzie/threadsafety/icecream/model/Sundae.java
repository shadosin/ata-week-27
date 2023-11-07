package com.kenzie.threadsafety.icecream.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A sundae servable to ice cream shop customers.
 */
public class Sundae {
    private final List<Flavor> scoops = new ArrayList<>();

    /**
     * Add a scoop of the given flavor to the sundae.
     * @param flavor The flavor to add
     */
    public void addScoop(Flavor flavor) {
        scoops.add(flavor);
    }

    public List<Flavor> getScoops() {
        return new ArrayList<>(scoops);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sundae sundae = (Sundae) o;
        return getScoops().equals(sundae.getScoops());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScoops());
    }

    @Override
    public String toString() {
        return "Sundae{" +
            "scoops=" + scoops +
            '}';
    }
}
