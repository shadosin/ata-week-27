package com.kenzie.groupwork.complianceenforcer;

import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesRequest;
import com.kenzie.executorservices.ringupdatescheck.customer.CustomerService;
import com.kenzie.executorservices.ringupdatescheck.devicecommunication.RingDeviceCommunicatorService;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.GetDeviceSystemInfoRequest;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceFirmwareVersion;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.UpdateDeviceFirmwareRequest;
import com.kenzie.executorservices.ringupdatescheck.util.KnownRingDeviceFirmwareVersions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Phase1Test {
    private static final int NUM_ITERATIONS = 5;
    private static final String TRIAL_CUSTOMER_ID = "1";

    private CustomerService customerService;
    private RingDeviceCommunicatorService deviceCommunicatorService;
    private ComplianceEnforcer complianceEnforcer;
    private ReferenceEnforcer referenceEnforcer;
    private List<String> deviceIds;

    @BeforeEach
    public void setup() {
        customerService = CustomerService.getClient();
        deviceCommunicatorService = RingDeviceCommunicatorService.getClient();
        complianceEnforcer = new ComplianceEnforcer(customerService, deviceCommunicatorService);
        referenceEnforcer = new ReferenceEnforcer(customerService, deviceCommunicatorService);
        deviceIds = customerService.getCustomerDevices(GetCustomerDevicesRequest.builder()
                .withCustomerId(TRIAL_CUSTOMER_ID)
                .build()).getDeviceIds();
    }

    @Test
    public void triggerUpdates_doesNotThrowFutureExceptions(){
        try {
            Method m = complianceEnforcer.getClass().getDeclaredMethod("triggerUpdates",
                    List.class, RingDeviceFirmwareVersion.class);
            List<Class> exceptions = List.of(m.getExceptionTypes());
            assertTrue(exceptions.size() == 0,
                    "triggerUpdates should not throw any non-runtime exceptions.\n" +
                    "A try-catch block should be used to catch any exceptions and deal with them. as they occur");
        } catch (NoSuchMethodException e) {
            fail("triggerUpdates has been modified. Please return spelling to triggerUpdates, " +
                    "and parameters to List<String> and RingDeviceFirmwareVersion");
        }
    }

    @Test
    public void updateDevices_wantBlinky_updatesToBlinky(){
        RingDeviceCommunicatorService deviceCommunicatorSpy = spy(RingDeviceCommunicatorService.getClient());
        ArgumentCaptor<UpdateDeviceFirmwareRequest> requestCaptor =
                ArgumentCaptor.forClass(UpdateDeviceFirmwareRequest.class);

        ComplianceEnforcer enforcer = new ComplianceEnforcer(customerService,deviceCommunicatorSpy);

        RingDeviceFirmwareVersion version = KnownRingDeviceFirmwareVersions.BLINKY;

        enforcer.updateDevices(deviceIds,version);

        verify(deviceCommunicatorSpy,atLeast(1)).updateDeviceFirmware(requestCaptor.capture());
        UpdateDeviceFirmwareRequest request = requestCaptor.getValue();

        assertEquals(version, request.getVersion(), "Make sure you are updating to the desired version, not a hard coded value");

    }

    @Test
    public void updateDevices_wantPinky_updatesToPinky(){
        RingDeviceCommunicatorService deviceCommunicatorSpy = spy(RingDeviceCommunicatorService.getClient());
        ArgumentCaptor<UpdateDeviceFirmwareRequest> requestCaptor =
                ArgumentCaptor.forClass(UpdateDeviceFirmwareRequest.class);

        ComplianceEnforcer enforcer = new ComplianceEnforcer(customerService,deviceCommunicatorSpy);

        RingDeviceFirmwareVersion version = KnownRingDeviceFirmwareVersions.PINKY;

        enforcer.updateDevices(deviceIds,version);

        verify(deviceCommunicatorSpy,atLeast(1)).updateDeviceFirmware(requestCaptor.capture());
        UpdateDeviceFirmwareRequest request = requestCaptor.getValue();

        assertEquals(version, request.getVersion(), "Make sure you are updating to the desired version, not a hard coded value");

    }


    @Test
    public void updateDevices_afterRefactoring_failsToUpdateKnownFailingDevices() throws Exception {
        // GIVEN
        // A known list of devices
        // A reference of which devices should fail
        List<String> refIds = referenceEnforcer.updateDevices(deviceIds, KnownRingDeviceFirmwareVersions.PINKY);

        // WHEN
        // We attempt to update
        List<String> failedIds = complianceEnforcer.updateDevices(deviceIds, KnownRingDeviceFirmwareVersions.PINKY);

        // THEN
        // The failed IDs contains the known failures
        assertTrue(failedIds.containsAll(refIds) && refIds.size() == failedIds.size(),
                String.format("Expected known devices %s to fail update: got %s!", refIds, failedIds));

    }

    @Test
    void updateDevices_afterRefactoring_hasImprovedPerformance() throws Exception {
        // GIVEN
        // A known list of devices
        // A reference time to update the devices
        double referenceAvg = runProfile(
                () -> referenceEnforcer.updateDevices(deviceIds, KnownRingDeviceFirmwareVersions.PINKY));

        // WHEN
        // We update the devices with the improved algorithm
        double improvedAvg = runProfile(
                () -> complianceEnforcer.updateDevices(deviceIds, KnownRingDeviceFirmwareVersions.PINKY));

        // THEN
        // The average run time has improved
        double benchmark = referenceAvg / 2.0;
        assertTrue(improvedAvg < benchmark,
                String.format("Expected updateDevices to improve reference implementation average runtime [%02.02f] "
                        + "by at least half, to [%02.02f]. Measured average runtime was [%02.02f]!",
                        referenceAvg, benchmark, improvedAvg));
    }

    private double runProfile(Runnable trial) {
        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            trial.run();
        }
        long endTime = System.nanoTime();
        double avgTime = (double)(endTime - startTime) / 1000000000.0 / (double)NUM_ITERATIONS;
        return avgTime;
    }
}
