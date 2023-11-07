package com.kenzie.executorservices.ringupdatescheck.util;

import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceFirmwareVersion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RingDeviceFirmwareVersionComparatorTest {
    private RingDeviceFirmwareVersionComparator comparator;

    @BeforeEach
    void setup() {
        comparator = new RingDeviceFirmwareVersionComparator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"119.0", "12.2", "12.1.44", "12.1.43.1"})
    void compare_withInOrderVersions_ReturnsLessThanZero(String rightVersionString) {
        // GIVEN
        String leftVersionString = "12.1.43";
        RingDeviceFirmwareVersion leftVersion = RingDeviceFirmwareVersion.builder()
                .withVersionNumber(leftVersionString)
                .build();
        RingDeviceFirmwareVersion rightVersion = RingDeviceFirmwareVersion.builder()
                .withVersionNumber(rightVersionString)
                .build();

        // WHEN
        int result = comparator.compare(leftVersion, rightVersion);

        // THEN
        assertTrue(
            result < 0,
            String.format("Expected comparator to put version %s before %s, but did not", leftVersion, rightVersion)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"200", "1.0", "12.1.43.22"})
    void compare_withEqualVersions_ReturnsZero(String versionString) {
        // GIVEN
        RingDeviceFirmwareVersion leftVersion = RingDeviceFirmwareVersion.builder()
                .withVersionNumber(versionString)
                .build();
        RingDeviceFirmwareVersion rightVersion = RingDeviceFirmwareVersion.builder()
                .withVersionNumber(versionString)
                .build();

        // WHEN
        int result = comparator.compare(leftVersion, rightVersion);

        // THEN
        assertEquals(
            0,
            result,
            String.format("Expected comparator to evaluate version %s equal to %s, but did not",
                leftVersion,
                rightVersion)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.0", "2.1", "12.1", "12.1.42"})
    void compare_withReverseOrderVersions_ReturnsGreaterThanZero(String rightVersionString) {
        // GIVEN
        String leftVersionString = "12.1.43";
        RingDeviceFirmwareVersion leftVersion = RingDeviceFirmwareVersion.builder()
                .withVersionNumber(leftVersionString)
                .build();
        RingDeviceFirmwareVersion rightVersion = RingDeviceFirmwareVersion.builder()
                .withVersionNumber(rightVersionString)
                .build();

        // WHEN
        int result = comparator.compare(leftVersion, rightVersion);

        // THEN
        assertTrue(
            result > 0,
            String.format("Expected comparator to put version %s after %s, but did not", leftVersion, rightVersion)
        );
    }
}
