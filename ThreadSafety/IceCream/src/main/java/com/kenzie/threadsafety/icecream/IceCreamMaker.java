package com.kenzie.threadsafety.icecream;

import com.kenzie.threadsafety.icecream.dao.CartonDao;
import com.kenzie.threadsafety.icecream.model.Carton;
import com.kenzie.threadsafety.icecream.model.Flavor;

/**
 * The machine for making a batch/carton of ice cream.
 */
public class IceCreamMaker implements Runnable {
    private final CartonDao cartonDao;
    private final FlavorRequestQueue requests;
    private boolean listening = true;

    public IceCreamMaker(CartonDao cartonDao, FlavorRequestQueue requests) {
        this.cartonDao = cartonDao;
        this.requests = requests;
    }

    @Override
    public void run() {
        while (listening) {
            Flavor flavor = requests.nextNeededFlavor();
            Carton carton = prepareIceCreamCarton(flavor);
            cartonDao.addCarton(carton);
        }
    }

    /**
     * Prepares a carton of ice cream of the provided flavor.
     * @param flavor The flavor of ice cream to make
     * @return A full Carton of the requested flavor.
     */
    private Carton prepareIceCreamCarton(Flavor flavor) {
        //System.out.println("Creating a carton of " + flavor);
        mix();
        freeze();

        return Carton.makeCarton(flavor);
    }

    private void mix() {
        //System.out.println("Mixing ingredients");
    }

    private void freeze() {
        //System.out.println("Freezing!");
    }
}
