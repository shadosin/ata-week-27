package com.kenzie.executorservices.ringupdatescheck.test.wrapper;

import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceFirmwareVersion;
import com.kenzie.executorservices.ringupdatescheck.checker.DeviceCheckTask;
import com.kenzie.executorservices.ringupdatescheck.checker.DeviceChecker;
import com.kenzie.test.infrastructure.wrapper.WrapperBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Wraps the DeviceCheckTask class.
 */
public class DeviceCheckTaskWrapper extends WrapperBase {
    /**
     * Creates a wrapper instance with the specified underlying object.
     * @param wrappedInstance the instance to be wrapped
     */
    public DeviceCheckTaskWrapper(Object wrappedInstance) {
        super(wrappedInstance);
    }

    /**
     * Constructs a wrapper from device ID, firmware version id.
     * @param deviceChecker The DeviceChecker currently being used
     * @param deviceId The device ID the wrapped task is intended for
     * @param version  The desired RingDeviceFirmwareVersion to update to
     */
    public DeviceCheckTaskWrapper(DeviceChecker deviceChecker, String deviceId, RingDeviceFirmwareVersion version) {
        this(DeviceCheckTaskWrapper.constructInstanceFromArgs(deviceChecker, deviceId, version));
    }

    @Override
    public Class<?> getWrappedClass() {
        return DeviceCheckTask.class;
    }

    /**
     * Invokes the run() method if it exists. Otherwise, will fail assertion.
     */
    public void run() {
        Method runMethod = getMethod("run");
        invokeVoidInstanceMethod(runMethod);
    }

    private static DeviceCheckTask constructInstanceFromArgs(final DeviceChecker deviceChecker,
                                                             final String deviceId,
                                                             final RingDeviceFirmwareVersion version) {
        DeviceCheckTask task = null;

        Constructor<?> constructor = getConstructor(
                DeviceCheckTask.class, DeviceChecker.class, String.class, RingDeviceFirmwareVersion.class
        );

        try {
            // Either way, DeviceChecker must be the 0th argument (instructions in the class itself)
            if (constructor.getParameterTypes()[1].equals(String.class)) {
                task = (DeviceCheckTask) constructor.newInstance(deviceChecker, deviceId, version);
            } else if (constructor.getParameterTypes()[1].equals(RingDeviceFirmwareVersion.class)) {
                task = (DeviceCheckTask) constructor.newInstance(deviceChecker, version, deviceId);
            } else {
                fail("Didn't find an expected constructor with DeviceChecker as first argument, " +
                        "and other necessary fields. Constructor args were: " +
                        Arrays.toString(constructor.getParameterTypes())
                );
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail("Encountered exception trying to instantiate a DeviceCheckTask: " + e);
        }

        return task;
    }
}