package com.kenzie.executorservices.ringupdatescheck.model.devicecommunication;

import java.util.Arrays;
import java.util.Objects;


public class UpdateDeviceFirmwareRequest extends Object  {

    /**
     * Statically creates a builder instance for UpdateDeviceFirmwareRequest.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Fluent builder for instances of UpdateDeviceFirmwareRequest.
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

        protected RingDeviceFirmwareVersion version;
        /**
         * Sets the value of the field "version" to be used for the constructed object.
         * @param version
         *   The value of the "version" field.
         * @return
         *   This builder.
         */
        public Builder withVersion(RingDeviceFirmwareVersion version) {
            this.version = version;
            return this;
        }

        /**
         * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
         * @param instance
         *   The instance to be populated.
         */
        protected void populate(UpdateDeviceFirmwareRequest instance) {
            instance.setDeviceId(this.deviceId);
            instance.setVersion(this.version);
        }

        /**
         * Builds an instance of UpdateDeviceFirmwareRequest.
         * <p>
         * The built object has its fields set to the values given when calling the "with*" methods of this builder.
         * </p>
         */
        public UpdateDeviceFirmwareRequest build() {
            UpdateDeviceFirmwareRequest instance = new UpdateDeviceFirmwareRequest();

            populate(instance);

            return instance;
        }
    };

    private String deviceId;
    private RingDeviceFirmwareVersion version;

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public RingDeviceFirmwareVersion getVersion() {
        return this.version;
    }

    public void setVersion(RingDeviceFirmwareVersion version) {
        this.version = version;
    }

    private static final int classNameHashCode =
            internalHashCodeCompute("com.amazon.ata.executorservice.classroom.devicecommunication.UpdateDeviceFirmwareRequest");

    /**
     * HashCode implementation for UpdateDeviceFirmwareRequest
     * based on java.util.Arrays.hashCode
     */
    @Override
    public int hashCode() {
        return internalHashCodeCompute(
                classNameHashCode,
                getDeviceId(),
                getVersion());
    }

    private static int internalHashCodeCompute(Object... objects) {
        return Arrays.hashCode(objects);
    }

    /**
     * Equals implementation for UpdateDeviceFirmwareRequest
     * based on instanceof and Object.equals().
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof UpdateDeviceFirmwareRequest)) {
            return false;
        }

        UpdateDeviceFirmwareRequest that = (UpdateDeviceFirmwareRequest) other;

        return
                Objects.equals(getDeviceId(), that.getDeviceId())
                        && Objects.equals(getVersion(), that.getVersion());
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
        ret.append("UpdateDeviceFirmwareRequest(");

        ret.append("deviceId=");
        ret.append(String.valueOf(deviceId));
        ret.append(", ");

        ret.append("version=");
        ret.append(String.valueOf(version));
        ret.append(")");

        return ret.toString();
    }

}
