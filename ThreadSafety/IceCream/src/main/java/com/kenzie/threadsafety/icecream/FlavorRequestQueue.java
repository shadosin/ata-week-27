package com.kenzie.threadsafety.icecream;

import com.kenzie.threadsafety.icecream.model.Flavor;

import java.util.LinkedList;
import java.util.Queue;

public class FlavorRequestQueue {
    private final Queue<Flavor> flavorQueue;

    public FlavorRequestQueue() {
        flavorQueue = new LinkedList<>();
    }

    public synchronized void needFlavor(Flavor flavor) {
        flavorQueue.add(flavor);
    }
    private synchronized Flavor pollFlavor(){
       return flavorQueue.poll();
    }

    public Flavor nextNeededFlavor() {
        Flavor flavor = pollFlavor();
        while (flavor == null) {
            try {
                //Do not move or remove the sleep command
                Thread.sleep(10L);
                flavor = pollFlavor();
            } catch (InterruptedException e) {
                System.out.println("!!!Interrupted waiting for flavor request!!!");
                e.printStackTrace();
                throw new RuntimeException("Interrupted waiting for flavor request!", e);
            }
        }
        return flavor;
    }

    public int requestCount() {
        return flavorQueue.size();
    }
}
