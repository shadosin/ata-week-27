package com.kenzie.executorservices.ringupdatescheck.devicecommunication;

import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.GetDeviceSystemInfoRequest;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.GetDeviceSystemInfoResponse;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceFirmwareVersion;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceSystemInfo;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.UpdateDeviceFirmwareRequest;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.UpdateDeviceFirmwareResponse;
import com.kenzie.executorservices.ringupdatescheck.util.KnownRingDeviceFirmwareVersions;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Objects;

/**
 * Mocks service to access individual Ring devices, including querying
 * diagnostic/system info, and initiating firmware updates.
 *
 * Use the static method, getClient(), to obtain an instance to communicate
 * with the service.
 */
public class RingDeviceCommunicatorService {
    private static final Map<Integer, RingDeviceFirmwareVersion> versionMapping =
        new ImmutableMap.Builder<Integer, RingDeviceFirmwareVersion>()
            .put(0, KnownRingDeviceFirmwareVersions.PINKY)
            .put(1, KnownRingDeviceFirmwareVersions.PINKY)
            .put(2, KnownRingDeviceFirmwareVersions.PINKY)
            .put(3, KnownRingDeviceFirmwareVersions.BLINKY)
            .put(4, KnownRingDeviceFirmwareVersions.INKY)
            .put(5, KnownRingDeviceFirmwareVersions.INKY)
            .build();

    private static final RingDeviceCommunicatorService SINGLETON = new RingDeviceCommunicatorService();

    /**
     * Returns a client to use against the RingDeviceCommunicatorService.
     * @return a service client
     */
    public static RingDeviceCommunicatorService getClient() {
        return SINGLETON;
    }

    /**
     * Returns the system info for the requested device.
     * @param request The request containing the device identifier
     * @return The response containing the device's system info
     */
    public GetDeviceSystemInfoResponse getDeviceSystemInfo(final GetDeviceSystemInfoRequest request) {
        log(String.format("Received system info request for device %s", request.getDeviceId()));

        try {
            Thread.sleep(Math.abs(Objects.hash(request.getDeviceId())) % 200);
        } catch (InterruptedException e) {
            System.out.println("Pardon the interruption!");
        }

        log(String.format("Returned system info response for device %s", request.getDeviceId()));

        RingDeviceFirmwareVersion version =
            versionMapping.get(Math.abs(Objects.hash(request.getDeviceId())) % versionMapping.size());
        return GetDeviceSystemInfoResponse.builder()
                .withSystemInfo(RingDeviceSystemInfo.builder()
                                                    .withDeviceId(request.getDeviceId())
                                                    .withDeviceFirmwareVersion(version)
                                                    .build())
                .build();
    }

    /**
     * Attempts to update the firmware on the given device to the given firmware version, returning
     * success status in response object.
     * @param request The request specifying device and firmware version
     * @return The response object indicating the success status
     */
    public UpdateDeviceFirmwareResponse updateDeviceFirmware(final UpdateDeviceFirmwareRequest request) {
        log(String.format("Received request to upgrade device %s to version %s",
            request.getDeviceId(),
            request.getVersion())
        );

        try {
            Thread.sleep(200 + Math.abs(Objects.hash(request.getDeviceId())) % 200);
        } catch (InterruptedException e) {
            System.out.println("Pardon the interruption!");
        }

        boolean success = (Objects.hash(request.getDeviceId()) % 10) > 2;

        log(String.format("%s request to upgrade device %s to version %s",
            success ? "Completed" : "Failed",
            request.getDeviceId(),
            request.getVersion())
        );

        return UpdateDeviceFirmwareResponse.builder()
                .withDeviceId(request.getDeviceId())
                .withVersion(request.getVersion())
                .withWasSuccessful(success)
                .build();
    }

    private void log(String message) {
        System.out.println("[RingDeviceCommunicatorService] " + message);
    }
}
