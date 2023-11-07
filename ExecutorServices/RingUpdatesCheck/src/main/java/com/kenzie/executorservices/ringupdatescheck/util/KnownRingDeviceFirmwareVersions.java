package com.kenzie.executorservices.ringupdatescheck.util;

import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceFirmwareVersion;

/**
 * Utility class containing several known version numbers (used by test/mock code)
 * and a method for comparing versions.
 */
public class KnownRingDeviceFirmwareVersions {
    /**
     * Most recent version.
     */
    public static final RingDeviceFirmwareVersion PINKY = RingDeviceFirmwareVersion.builder()
                                                                                   .withVersionNumber("11.0")
                                                                                   .build();
    /**
     * Version before that.
     */
    public static final RingDeviceFirmwareVersion INKY = RingDeviceFirmwareVersion.builder()
            .withVersionNumber("10.9")
            .build();
    /**
     * A really old version from the ancients.
     */
    public static final RingDeviceFirmwareVersion BLINKY = RingDeviceFirmwareVersion.builder()
            .withVersionNumber("3.141")
            .build();

    /**
     * Determines whether the provided version is up-to-date, compared to the provided approved version.
     * @param version The version to check.
     * @param approved The approved version.
     * @return true if the version indicates that it needs to updated compared to the approved version,
     *         false otherwise.
     */
    public static boolean needsUpdate(RingDeviceFirmwareVersion version, RingDeviceFirmwareVersion approved) {
        RingDeviceFirmwareVersionComparator comparator = new RingDeviceFirmwareVersionComparator();
        return comparator.compare(version, approved) < 0;
    }
}
