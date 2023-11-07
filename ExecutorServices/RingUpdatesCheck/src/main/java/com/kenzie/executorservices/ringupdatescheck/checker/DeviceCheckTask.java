package com.kenzie.executorservices.ringupdatescheck.checker;

import com.kenzie.executorservices.ringupdatescheck.devicecommunication.RingDeviceCommunicatorService;

/**
 * A task to check a single device's version against a desired latest
 * version, requesting a firmware update if appropriate.
 *
 * PARTICIPANTS: Implement this class in Phase 1
 */
public class DeviceCheckTask {
    private RingDeviceCommunicatorService ringDeviceCommunicatorService;
    private DeviceChecker deviceChecker;

    /**
     * Constructs a DeviceCheckTask with the given dependencies and parameters.
     *
     * PARTICIPANTS: If you add constructor parameters, add them AFTER the DeviceChecker
     * argument. If you add parameters before the DeviceChecker, your tests will fail.
     *
     * @param deviceChecker The DeviceChecker to use while executing this task
     */
    public DeviceCheckTask(DeviceChecker deviceChecker) {
        this.ringDeviceCommunicatorService = deviceChecker.getRingDeviceCommunicatorService();
        this.deviceChecker = deviceChecker;
    }
}
