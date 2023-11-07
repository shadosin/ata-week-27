package com.kenzie.threadsafety.counter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceCounterTest {

    @Test
    public void getCounter_2000Threads_2000() {
        // GIVEN
        int numThreads = 2000;
        RaceCounter raceCounter = new RaceCounter();

        // WHEN
        raceCounter.startCounter(numThreads);

        try {
            // make sure the threads have completed
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // main thread interrupted
            Assertions.fail("Threads were interrupted before a result could be attained.");
        }

        // THEN
        Assertions.assertEquals(numThreads, raceCounter.getCounter(), "The final result of counter is " +
                "not what was expected.");
    }

    @Test
    public void counter_isAtomicInteger() {
        // GIVEN
        RaceCounter raceCounter = new RaceCounter();

        // WHEN
        Field atomicField = getAtomicIntegerField(raceCounter);

        Boolean isAtomic = atomicField != null;

        // THEN
        Assertions.assertTrue(isAtomic, "Counter has not been properly initialized as an atomic type.");
    }

    @Test
    public void startCounter_threadsRunning_2008() {
        // GIVEN
        RaceCounter raceCounter = new RaceCounter();

        // WHEN
        // Using reflection to grab the new counter that should be atomic.
        Field atomicField = getAtomicIntegerField(raceCounter);

        AtomicInteger atomicCounter = null;

        try {
            atomicCounter = (AtomicInteger) atomicField.get(raceCounter);

            // change the initial value of the counter
            atomicCounter.set(8);

            raceCounter.startCounter(2000);

            try {
                // make sure the threads have completed
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // main thread interrupted
                Assertions.fail("Threads were interrupted before a result could be attained.");
            }

            // THEN
            Assertions.assertEquals(2008, atomicCounter.get(), "The final result of counter is not what was expected.");
        } catch (IllegalAccessException e) {
            //The IllegalAccessException is thrown when attempting to cast a class of one type into an incompatible type
            Assertions.fail("Counter has not been properly initialized as an atomic type.");
            return;
        }
    }

    /**
     * Uses reflection to access the private LoadingCache field
     * This is only being done for test verification purposes.
     * @param raceCounter Instance of the RacingCounter class
     * @return the field that should contain the LoadingCache cache.
     */
    private Field getAtomicIntegerField(RaceCounter raceCounter) {
        Class<?> counterClass = raceCounter.getClass();
        Field[] fields = counterClass.getDeclaredFields();
        Field counterField = null;

        for (Field field : fields) {
            if (field.getType().equals(AtomicInteger.class)) {
                counterField = field;
                field.setAccessible(true);
            }
        }
        return counterField;
    }

}
