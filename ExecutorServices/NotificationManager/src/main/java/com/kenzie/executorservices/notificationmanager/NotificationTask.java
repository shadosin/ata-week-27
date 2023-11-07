package com.kenzie.executorservices.notificationmanager;

public class NotificationTask implements Runnable {

    private String notificationMessage;
    private String distributionConfiguration;

    /**
     * Constructor for SendNotifications.
     * @param config Distribution configuration.
     * @param message Message to print.
     */
    public NotificationTask(String config, String message) {
        distributionConfiguration = config;
        notificationMessage = message;
    }

    /**
     * Message printed on execution.
     */
    public void run() {
        System.out.println(distributionConfiguration + " notification sent out: " + notificationMessage);
    }
}
