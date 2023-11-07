package com.kenzie.executorservices.ringupdatescheck.test;

import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesRequest;
import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesResponse;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.GetDeviceSystemInfoRequest;
import com.kenzie.executorservices.ringupdatescheck.checker.DeviceChecker;
import com.kenzie.executorservices.ringupdatescheck.customer.CustomerService;
import com.kenzie.executorservices.ringupdatescheck.devicecommunication.RingDeviceCommunicatorService;
import com.kenzie.executorservices.ringupdatescheck.util.KnownRingDeviceFirmwareVersions;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Phase3Test {
    private static final int NUM_ITERATIONS = 5;
    private static final String TRIAL_CUSTOMER_ID = "1";

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
    void deviceChecker_checkDevicesConcurrently_callsGetDeviceSystemInfoForDevice() {
        // GIVEN
        String customerId = "CUST1234";
        String deviceId = "1234";
        List<String> devicesList = ImmutableList.of(deviceId);
        // customer service spy to force reply including just the device(s) above
        GetCustomerDevicesRequest gcdRequest = GetCustomerDevicesRequest.builder().withCustomerId(customerId).build();
        GetCustomerDevicesResponse gcdResponse = GetCustomerDevicesResponse.builder()
                .withCustomerId(customerId)
                .withDeviceIds(devicesList)
                .build();
        when(customerServiceSpy.getCustomerDevices(gcdRequest)).thenReturn(gcdResponse);
        // RingDeviceCommunicatorService spy to verify call to GetDeviceSystemInfo operation

        // WHEN
        deviceChecker.checkDevicesConcurrently(customerId, KnownRingDeviceFirmwareVersions.PINKY);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Received InterruptedException: " + e);
        }

        // THEN
        // expected device system info request
        GetDeviceSystemInfoRequest gdsiRequest = GetDeviceSystemInfoRequest.builder().withDeviceId(deviceId).build();
        verify(
            deviceCommunicatorServiceSpy,
            description("Expected checkDevicesIteratively to result in GetDeviceSystemInfo call with device " + deviceId)
        ).getDeviceSystemInfo(gdsiRequest);
    }

    @Test
    void deviceChecker_checkDevicesIteratively_Timer() {
        runProfile(
            () -> deviceChecker.checkDevicesIteratively(TRIAL_CUSTOMER_ID, KnownRingDeviceFirmwareVersions.PINKY),
            String.format("checkDevicesIteratively(%s)", TRIAL_CUSTOMER_ID)
        );
    }

    @Test
    void deviceChecker_checkDevicesConcurrently_Timer() {
        runProfile(
            () -> deviceChecker.checkDevicesConcurrently(TRIAL_CUSTOMER_ID, KnownRingDeviceFirmwareVersions.PINKY),
            String.format("checkDevicesConcurrently(%s)", TRIAL_CUSTOMER_ID)
        );
    }

    private void runProfile(Runnable trial, String trialDescription) {
        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            trial.run();
        }
        long endTime = System.nanoTime();
        String avgTime = Double.toString((endTime - startTime) / 1000000000.0 / (double)NUM_ITERATIONS);
        System.out.println(String.format("On average, %s: %s", trialDescription, avgTime));
    }
}
