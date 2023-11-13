package com.kenzie.executorservices.ringupdatescheck.checker;

import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesRequest;
import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesResponse;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceFirmwareVersion;
import com.kenzie.executorservices.ringupdatescheck.customer.CustomerService;
import com.kenzie.executorservices.ringupdatescheck.devicecommunication.RingDeviceCommunicatorService;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.UpdateDeviceFirmwareRequest;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Utility object for checking version status of devices, and updating
 * them if necessary.
 *
 * For instructional purposes, two implementations of the same logic
 * will be created: checkDevicesIteratively, and checkDevicesConcurrently.
 */
public class DeviceChecker {
    private final CustomerService customerService;
    private final RingDeviceCommunicatorService ringDeviceCommunicatorService;

    /**
     * Constructs a DeviceChecker with the provided dependencies.
     *
     * PARTICIPANTS: Do not change the signature of this constructor
     * @param customerService The CustomerService client to use for Customer operations
     * @param ringDeviceCommunicatorService The RingDeviceCommunicatorService client to use for
     *                                      device communication operations
     */
    public DeviceChecker(CustomerService customerService, RingDeviceCommunicatorService ringDeviceCommunicatorService) {
        this.customerService = customerService;
        this.ringDeviceCommunicatorService = ringDeviceCommunicatorService;
    }

    /**
     * Iteratively checks all devices for the given customer.
     * @param customerId The customer to check devices for
     * @param version The firmware version that we want all devices updated to
     * @return The number of devices that were checked
     */
    public int checkDevicesIteratively(final String customerId, RingDeviceFirmwareVersion version) {
        // PARTICIPANTS: implement in Phase 2
        GetCustomerDevicesRequest request = GetCustomerDevicesRequest.builder().withCustomerId(customerId).build();
        GetCustomerDevicesResponse response = customerService.getCustomerDevices(request);
        List<String> devices = response.getDeviceIds();
        int devicesChecked = 0;
        for(String deviceId: devices){
            DeviceCheckTask deviceCheckTask = new DeviceCheckTask(this, deviceId, version);
            deviceCheckTask.run();
            devicesChecked ++;
        }
        return devicesChecked;
    }

    /**
     * Concurrently checks all devices for the given customer.
     * @param customerId The customer to check devices for
     * @param version The firmware version that we want all devices updated to
     * @return The number of devices that were checked
     */
    public int checkDevicesConcurrently(final String customerId, RingDeviceFirmwareVersion version) {
        // PARTICIPANTS: implement in Phase 3
        ExecutorService service = Executors.newCachedThreadPool();
        GetCustomerDevicesRequest request = GetCustomerDevicesRequest.builder().withCustomerId(customerId).build();
        GetCustomerDevicesResponse response = customerService.getCustomerDevices(request);
        List<String> devices = response.getDeviceIds();
        int devicesChecked = 0;
        for(String deviceId: devices){
            DeviceCheckTask deviceCheckTask = new DeviceCheckTask(this, deviceId, version);
            service.submit(deviceCheckTask);
            devicesChecked ++;
        }
        service.shutdown();
        return devicesChecked;

    }

    /**
     * Updates the device to the specified version.
     * @param deviceId The device identifier of the device to update
     * @param version The version the device should be updated to
     */
    public void updateDevice(final String deviceId, final RingDeviceFirmwareVersion version) {
        System.out.println(String.format("[DeviceChecker] Updating device %s to version %s", deviceId, version));

        // PARTICIPANTS: add remaining implementation here in Phase 4
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            ringDeviceCommunicatorService.updateDeviceFirmware(
                    UpdateDeviceFirmwareRequest.builder().withDeviceId(deviceId).withVersion(version).build());
        });
        service.shutdown();
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public RingDeviceCommunicatorService getRingDeviceCommunicatorService() {
        return ringDeviceCommunicatorService;
    }
}
