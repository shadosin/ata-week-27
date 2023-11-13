package com.kenzie.executorservices.notificationmanager;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationManager {

    /**
     * Method called to send notifications out.
     * @param tasks List of NotificationTasks tasks to be run
     */
    public void sendSaleNotificationsOut(List<NotificationTask> tasks) {
        //Implement this method
        ExecutorService executor = Executors.newCachedThreadPool();
        tasks.stream().forEach(executor::submit);
        executor.shutdown();
    }

}
