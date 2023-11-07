package com.kenzie.threadsafety.icecream;

import com.kenzie.threadsafety.icecream.FlavorRequestQueue;
import com.kenzie.threadsafety.icecream.IceCreamMaker;
import com.kenzie.threadsafety.icecream.dao.CartonDao;
import com.kenzie.threadsafety.icecream.model.Flavor;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.kenzie.threadsafety.icecream.model.Flavor.CHOCOLATE;
import static com.kenzie.threadsafety.icecream.model.Flavor.STRAWBERRY;
import static com.kenzie.threadsafety.icecream.model.Flavor.VANILLA;
import static org.junit.jupiter.api.Assertions.*;

public class Phase2Test {
    CartonDao cartonDao = new CartonDao();
    FlavorRequestQueue flavorRequestQueue = new FlavorRequestQueue();

    @Test
    public void flavorRequestQueue_10000Requests_handles10000RequestsForEachFlavor() throws Exception {
        // GIVEN
        // 20 ice cream makers, executing in their own threads
        List<IceCreamMaker> iceCreamMakers = IntStream.range(0, 20)
            .mapToObj(i -> new IceCreamMaker(cartonDao, flavorRequestQueue))
            .collect(Collectors.toList());
        ExecutorService executorService = Executors.newCachedThreadPool();
        iceCreamMakers.forEach(executorService::submit);
        executorService.shutdown();
        // 10K requests to be made at a time
        int numRequests = 10000;

        // WHEN
        // We actually make the requests
        IntStream.range(0, numRequests)
            .forEach(i -> flavorRequestQueue.needFlavor(CHOCOLATE));
        IntStream.range(0, numRequests)
                .forEach(i -> flavorRequestQueue.needFlavor(VANILLA));
        IntStream.range(0, numRequests)
                .forEach(i -> flavorRequestQueue.needFlavor(STRAWBERRY));
        Thread.sleep(6000L);

        // THEN
        // The service processes all the requests without deadlocking
        assertEquals(0, flavorRequestQueue.requestCount(),
                String.format("All requests not fulfilled! Completed - %d chocolate, %d vanilla, %d strawberry.",
                        cartonDao.inventoryOfFlavor(CHOCOLATE),
                        cartonDao.inventoryOfFlavor(VANILLA),
                        cartonDao.inventoryOfFlavor(STRAWBERRY)));
        assertEquals(numRequests, cartonDao.inventoryOfFlavor(CHOCOLATE),
                "Completed - " + cartonDao.inventoryOfFlavor(CHOCOLATE) + " chocolate cartons.");
        assertEquals(numRequests, cartonDao.inventoryOfFlavor(VANILLA),
                "Completed - " + cartonDao.inventoryOfFlavor(VANILLA) + " vanilla cartons.");
        assertEquals(numRequests, cartonDao.inventoryOfFlavor(STRAWBERRY),
                "Completed - " + cartonDao.inventoryOfFlavor(STRAWBERRY) + " strawberry cartons.");
    }


    @Test
    public void nextNeededFlavor_notSynchronized(){
        try {
            //GIVEN
            Method method = FlavorRequestQueue.class.getMethod("nextNeededFlavor");

            //WHEN
            boolean isSync = Modifier.isSynchronized(method.getModifiers());

            //THEN
            assertFalse(isSync,"nextNeededFlavor should not be synchronized. Thread safety should be " +
                    " achieved another way");
        }catch(NoSuchMethodException e){
            fail("FlavorRequestQueue has been modified to not have the nextNeededFlavor method as expected" +
                    "Please remove any parameters added and return name to nextNeededFlavor.");
        }
    }

    @Test
    public void nextNeededFlavor_isSynchronized(){
        try {
            //GIVEN
            Method method = FlavorRequestQueue.class.getMethod("needFlavor", Flavor.class);

            //WHEN
            boolean isSync = Modifier.isSynchronized(method.getModifiers());

            //THEN
            assertTrue(isSync,"nextFlavor should be synchronized to make flavorQueue thread safe.");
        }catch(NoSuchMethodException e){
            fail("FlavorRequestQueue has been modified to not have the nextFlavor method as expected" +
                    "Please return the function to having one Flavor parameter and " +
                    "return name to nextFlavor.");
        }
    }

    @Test
    public void flavorQueue_isLinkedList(){
        try {
            //GIVEN
            FlavorRequestQueue flavorRequestQueue = new FlavorRequestQueue();

            Field field = FlavorRequestQueue.class.getDeclaredField("flavorQueue");

            field.setAccessible(true);

            Queue<Flavor> queue = (Queue<Flavor>) field.get(flavorRequestQueue);

            //THEN
            assertEquals(LinkedList.class, queue.getClass(), "flavorQueue's initialization " +
                    "should not be changed to achieve thread safety,\n as that defeats the purpose " +
                    "of this assignment. \nPlease change flavorQueue's assignment in the constructor back to LinkedList<>() " +
                    "\n and try a different way of making this thread safe.\n");

        }catch(NoSuchFieldException e){
            System.out.println(e);
            fail("flavorQueue name has been modified, please return the queue's name to flavorQueue");
        }catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("Test failed in an unexpected way: " + e.getMessage() + " Please contact your facilitator for assistance.");
        }catch(ClassCastException e){
            fail("flavorQueue modified to no longer be a queue. Please return its type to Queue<Flavor>");
        }
    }

}
