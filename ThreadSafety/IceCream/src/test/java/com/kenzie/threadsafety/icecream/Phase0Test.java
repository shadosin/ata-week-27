package com.kenzie.threadsafety.icecream;

import com.kenzie.threadsafety.icecream.FlavorRequestQueue;
import com.kenzie.threadsafety.icecream.IceCreamMaker;
import com.kenzie.threadsafety.icecream.dao.CartonDao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Phase0Test {
    CartonDao cartonDao = new CartonDao();
    FlavorRequestQueue flavorRequestQueue = new FlavorRequestQueue();

    @Test
    public void constructor_iceCreamMaker_succeeds() throws Exception {
        // GIVEN
        // We're running this test

        // WHEN
        // We create an IceCreamMaker
        IceCreamMaker iceCreamMaker = new IceCreamMaker(cartonDao, flavorRequestQueue);

        // THEN
        assertNotNull(iceCreamMaker, "Expected to be able to create an IceCreamMaker!");
    }

}
