package com.kenzie.threadsafety.counter;

public class RaceCounterApp {

    /**
     * Main method to instantiate and run Counter.
     * @param args Main method parameter
     */
    public static void main(String[] args) {
        RaceCounter raceCounter = new RaceCounter();

        raceCounter.startCounter(10000);

        System.out.println("Counter value: " + raceCounter.getCounter());
    }
}
