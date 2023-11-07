package com.kenzie.threadsafety.icecream.dao;

import com.kenzie.threadsafety.icecream.model.Carton;
import com.kenzie.threadsafety.icecream.model.Flavor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Provides access to cartons of ice cream in storage.
 */
public class CartonDao {
    private final Map<Flavor, List<Carton>> cartonsForFlavor = new HashMap<>();

    public CartonDao() {
    }

    public List<Carton> getCartons(List<Flavor> flavors) {
        return flavors.stream()
            .map(cartonsForFlavor::get)
            .map(list -> list.remove(0))
            .collect(Collectors.toList());
    }

    public int inventoryOfFlavor(Flavor flavor) {
        return cartonsForFlavor.get(flavor).size();
    }

    /**
     * Adds or replaces a Carton. The Carton may be empty.
     * @param carton A carton, empty or full.
     */
    public synchronized void addCarton(Carton carton) {
        Flavor flavor = carton.getFlavor();
        List<Carton> cartons = this.cartonsForFlavor.getOrDefault(flavor, new ArrayList<>());
        cartons.add(carton);
        cartonsForFlavor.put(flavor, cartons);
    }
}
