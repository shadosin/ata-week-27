package com.kenzie.executorservices.ringupdatescheck.model.devicecommunication;

import java.util.Arrays;
import java.util.Objects;

public class RingDeviceSystemInfo extends Object  {

    /**
     * Statically creates a builder instance for RingDeviceSystemInfo.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Fluent builder for instances of RingDeviceSystemInfo.
     */
    public static class Builder {

        protected String deviceId;
        /**
         * Sets the value of the field "deviceId" to be used for the constructed object.
         * @param deviceId
         *   The value of the "deviceId" field.
         * @return
         *   This builder.
         */
        public Builder withDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        protected RingDeviceFirmwareVersion deviceFirmwareVersion;
        /**
         * Sets the value of the field "deviceFirmwareVersion" to be used for the constructed object.
         * @param deviceFirmwareVersion
         *   The value of the "deviceFirmwareVersion" field.
         * @return
         *   This builder.
         */
        public Builder withDeviceFirmwareVersion(RingDeviceFirmwareVersion deviceFirmwareVersion) {
            this.deviceFirmwareVersion = deviceFirmwareVersion;
            return this;
        }

        /**
         * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
         * @param instance
         *   The instance to be populated.
         */
        protected void populate(RingDeviceSystemInfo instance) {
            instance.setDeviceId(this.deviceId);
            instance.setDeviceFirmwareVersion(this.deviceFirmwareVersion);
        }

        /**
         * Builds an instance of RingDeviceSystemInfo.
         * <p>
         * The built object has its fields set to the values given when calling the "with*" methods of this builder.
         * </p>
         */
        public RingDeviceSystemInfo build() {
            RingDeviceSystemInfo instance = new RingDeviceSystemInfo();

            populate(instance);

            return instance;
        }
    };

    private String deviceId;
    private RingDeviceFirmwareVersion deviceFirmwareVersion;

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public RingDeviceFirmwareVersion getDeviceFirmwareVersion() {
        return this.deviceFirmwareVersion;
    }

    public void setDeviceFirmwareVersion(RingDeviceFirmwareVersion deviceFirmwareVersion) {
        this.deviceFirmwareVersion = deviceFirmwareVersion;
    }

    private static final int classNameHashCode =
            internalHashCodeCompute("com.amazon.ata.executorservice.classroom.devicecommunication.RingDeviceSystemInfo");

    /**
     * HashCode implementation for RingDeviceSystemInfo
     * based on java.util.Arrays.hashCode
     */
    @Override
    public int hashCode() {
        return internalHashCodeCompute(
                classNameHashCode,
                getDeviceId(),
                getDeviceFirmwareVersion());
    }

    private static int internalHashCodeCompute(Object... objects) {
        return Arrays.hashCode(objects);
    }

    /**
     * Equals implementation for RingDeviceSystemInfo
     * based on instanceof and Object.equals().
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof RingDeviceSystemInfo)) {
            return false;
        }

        RingDeviceSystemInfo that = (RingDeviceSystemInfo) other;

        return
                Objects.equals(getDeviceId(), that.getDeviceId())
                        && Objects.equals(getDeviceFirmwareVersion(), that.getDeviceFirmwareVersion());
    }

    /**
     * Returns a string representation of this object. The content of any types marked with the
     * <a href="https://w.amazon.com/index.php/Coral/Model/XML/Traits#Sensitive">sensitive</a>
     * trait will be redacted.
     * <p/>
     * <b>Do not attempt to parse the string returned by this method.</b> Coral's <tt>toString</tt>
     * format is undefined and subject to change. To obtain a proper machine-readable representation
     * of this object, use Coral Serialize directly.
     * @see <a href="https://w.amazon.com/index.php/Coral/Serialize/Manual">Coral Serialize Manual</a>
     * @see <a href="https://w.amazon.com/index.php/Coral/Serialize/FAQ">Coral Serialize FAQ</a>
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("RingDeviceSystemInfo(");

        ret.append("deviceId=");
        ret.append(String.valueOf(deviceId));
        ret.append(", ");

        ret.append("deviceFirmwareVersion=");
        ret.append(String.valueOf(deviceFirmwareVersion));
        ret.append(")");

        return ret.toString();
    }

}
