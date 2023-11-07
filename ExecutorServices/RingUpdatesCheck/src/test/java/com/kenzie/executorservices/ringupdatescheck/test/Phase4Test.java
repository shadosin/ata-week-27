package com.kenzie.executorservices.ringupdatescheck.test;

import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesRequest;
import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesResponse;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.GetDeviceSystemInfoRequest;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.GetDeviceSystemInfoResponse;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceSystemInfo;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.UpdateDeviceFirmwareRequest;
import com.kenzie.executorservices.ringupdatescheck.checker.DeviceChecker;
import com.kenzie.executorservices.ringupdatescheck.customer.CustomerService;
import com.kenzie.executorservices.ringupdatescheck.devicecommunication.RingDeviceCommunicatorService;
import com.kenzie.executorservices.ringupdatescheck.util.KnownRingDeviceFirmwareVersions;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Phase4Test {
    private CustomerService customerServiceSpy;
    private RingDeviceCommunicatorService deviceCommunicatorServiceSpy;
    private DeviceChecker deviceChecker;

    @BeforeEach
    public void setup() {
        customerServiceSpy = spy(CustomerService.getClient());
        deviceCommunicatorServiceSpy = spy(RingDeviceCommunicatorService.getClient());
        deviceChecker = new DeviceChecker(customerServiceSpy, deviceCommunicatorServiceSpy);
    }

    @Test
    void deviceTracker_checkDevicesConcurrently_resultsInCallsToUpdateDeviceFirmwareForOutOfDateDevice() {
        // GIVEN
        // The customer to check devices for
        String customerId = "1234";
        String upToDateDeviceId = "456";
        String outOfDateDeviceId = "789";
        List<String> devices = ImmutableList.of(upToDateDeviceId, outOfDateDeviceId);
        // use CustomerService spy to mock device list response
        GetCustomerDevicesRequest gcdRequest = GetCustomerDevicesRequest.builder().withCustomerId(customerId).build();
        GetCustomerDevicesResponse gcdResponse = GetCustomerDevicesResponse.builder()
                .withCustomerId(customerId)
                .withDeviceIds(devices)
                .build();
        when(customerServiceSpy.getCustomerDevices(gcdRequest)).thenReturn(gcdResponse);
        // use RingDeviceCommunicatorService spy to mock device versions and verify firmware update request
        GetDeviceSystemInfoRequest gdsiRequestUpToDate = GetDeviceSystemInfoRequest.builder()
                .withDeviceId(upToDateDeviceId)
                .build();
        GetDeviceSystemInfoResponse gdsiResponseUpToDate = GetDeviceSystemInfoResponse.builder()
                .withSystemInfo(RingDeviceSystemInfo.builder()
                        .withDeviceId(upToDateDeviceId)
                        .withDeviceFirmwareVersion(KnownRingDeviceFirmwareVersions.PINKY)
                        .build())
                .build();
        GetDeviceSystemInfoRequest gdsiRequestOutOfDate = GetDeviceSystemInfoRequest.builder()
                .withDeviceId(outOfDateDeviceId)
                .build();
        GetDeviceSystemInfoResponse gdsiResponseOutOfDate = GetDeviceSystemInfoResponse.builder()
                .withSystemInfo(RingDeviceSystemInfo.builder()
                        .withDeviceId(outOfDateDeviceId)
                        .withDeviceFirmwareVersion(KnownRingDeviceFirmwareVersions.INKY)
                        .build())
                .build();
        when(deviceCommunicatorServiceSpy.getDeviceSystemInfo(gdsiRequestUpToDate)).thenReturn(gdsiResponseUpToDate);
        when(deviceCommunicatorServiceSpy.getDeviceSystemInfo(gdsiRequestOutOfDate)).thenReturn(gdsiResponseOutOfDate);

        // WHEN
        deviceChecker.checkDevicesConcurrently(customerId, KnownRingDeviceFirmwareVersions.PINKY);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Received InterruptedException: " + e);
        }

        // THEN
        UpdateDeviceFirmwareRequest expectedRequest = UpdateDeviceFirmwareRequest.builder()
                .withDeviceId(outOfDateDeviceId)
                .withVersion(KnownRingDeviceFirmwareVersions.PINKY)
                .build();
        UpdateDeviceFirmwareRequest notExpectedRequest = UpdateDeviceFirmwareRequest.builder()
                .withDeviceId(upToDateDeviceId)
                .withVersion(KnownRingDeviceFirmwareVersions.PINKY)
                .build();
        verify(deviceCommunicatorServiceSpy).updateDeviceFirmware(expectedRequest);
        verify(deviceCommunicatorServiceSpy, never()).updateDeviceFirmware(notExpectedRequest);
    }

    @Test
    void deviceTracker_checkDevicesConcurrently_resultsInCallsToUpdateDeviceFirmwareForOutOfDateDevice2() {
        // GIVEN
        // The customer to check devices for
        String customerId = "1234";
        String upToDateDeviceId = "456";
        String outOfDateDeviceId = "789";
        List<String> devices = ImmutableList.of(upToDateDeviceId, outOfDateDeviceId);
        // use CustomerService spy to mock device list response
        GetCustomerDevicesRequest gcdRequest = GetCustomerDevicesRequest.builder().withCustomerId(customerId).build();
        GetCustomerDevicesResponse gcdResponse = GetCustomerDevicesResponse.builder()
                .withCustomerId(customerId)
                .withDeviceIds(devices)
                .build();
        when(customerServiceSpy.getCustomerDevices(gcdRequest)).thenReturn(gcdResponse);
        // use RingDeviceCommunicatorService spy to mock device versions and verify firmware update request
        GetDeviceSystemInfoRequest gdsiRequestUpToDate = GetDeviceSystemInfoRequest.builder()
                .withDeviceId(upToDateDeviceId)
                .build();
        GetDeviceSystemInfoResponse gdsiResponseUpToDate = GetDeviceSystemInfoResponse.builder()
                .withSystemInfo(RingDeviceSystemInfo.builder()
                        .withDeviceId(upToDateDeviceId)
                        .withDeviceFirmwareVersion(KnownRingDeviceFirmwareVersions.INKY)
                        .build())
                .build();
        GetDeviceSystemInfoRequest gdsiRequestOutOfDate = GetDeviceSystemInfoRequest.builder()
                .withDeviceId(outOfDateDeviceId)
                .build();
        GetDeviceSystemInfoResponse gdsiResponseOutOfDate = GetDeviceSystemInfoResponse.builder()
                .withSystemInfo(RingDeviceSystemInfo.builder()
                        .withDeviceId(outOfDateDeviceId)
                        .withDeviceFirmwareVersion(KnownRingDeviceFirmwareVersions.BLINKY)
                        .build())
                .build();
        when(deviceCommunicatorServiceSpy.getDeviceSystemInfo(gdsiRequestUpToDate)).thenReturn(gdsiResponseUpToDate);
        when(deviceCommunicatorServiceSpy.getDeviceSystemInfo(gdsiRequestOutOfDate)).thenReturn(gdsiResponseOutOfDate);

        // WHEN
        deviceChecker.checkDevicesConcurrently(customerId, KnownRingDeviceFirmwareVersions.INKY);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Received InterruptedException: " + e);
        }

        // THEN
        UpdateDeviceFirmwareRequest expectedRequest = UpdateDeviceFirmwareRequest.builder()
                .withDeviceId(outOfDateDeviceId)
                .withVersion(KnownRingDeviceFirmwareVersions.INKY)
                .build();
        UpdateDeviceFirmwareRequest notExpectedRequest = UpdateDeviceFirmwareRequest.builder()
                .withDeviceId(upToDateDeviceId)
                .withVersion(KnownRingDeviceFirmwareVersions.INKY)
                .build();
        verify(deviceCommunicatorServiceSpy).updateDeviceFirmware(expectedRequest);
        verify(deviceCommunicatorServiceSpy, never()).updateDeviceFirmware(notExpectedRequest);
    }
}
