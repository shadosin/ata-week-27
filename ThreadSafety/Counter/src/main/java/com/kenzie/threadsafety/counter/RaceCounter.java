package com.kenzie.threadsafety.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceCounter {
    private int counter = 0;

    /**
     * Initializes the two threads and starts the counter.
     *
     * @param countTo The number to count up to. Also the number of threads to create.
     */
    public void startCounter(int countTo) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < countTo; i++) {
            executorService.submit(() -> {
                counter++;
            });
        }

        executorService.shutdown();
    }

    /**
     * Used to retrieve the current value of counter.
     * @return The current value of the counter.
     */
    public int getCounter() {
        return this.counter;
    }

}
