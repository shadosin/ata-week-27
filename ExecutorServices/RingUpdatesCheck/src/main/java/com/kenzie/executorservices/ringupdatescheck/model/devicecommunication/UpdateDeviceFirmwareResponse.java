package com.kenzie.executorservices.ringupdatescheck.model.devicecommunication;

import java.util.Arrays;
import java.util.Objects;

public class UpdateDeviceFirmwareResponse extends Object  {

    /**
     * Statically creates a builder instance for UpdateDeviceFirmwareResponse.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Fluent builder for instances of UpdateDeviceFirmwareResponse.
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

        protected boolean wasSuccessful;
        /**
         * Sets the value of the field "wasSuccessful" to be used for the constructed object.
         * @param wasSuccessful
         *   The value of the "wasSuccessful" field.
         * @return
         *   This builder.
         */
        public Builder withWasSuccessful(boolean wasSuccessful) {
            this.wasSuccessful = wasSuccessful;
            return this;
        }

        /**
         * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
         * @param instance
         *   The instance to be populated.
         */
        protected void populate(UpdateDeviceFirmwareResponse instance) {
            instance.setDeviceId(this.deviceId);
            instance.setVersion(this.version);
            instance.setWasSuccessful(this.wasSuccessful);
        }

        /**
         * Builds an instance of UpdateDeviceFirmwareResponse.
         * <p>
         * The built object has its fields set to the values given when calling the "with*" methods of this builder.
         * </p>
         */
        public UpdateDeviceFirmwareResponse build() {
            UpdateDeviceFirmwareResponse instance = new UpdateDeviceFirmwareResponse();

            populate(instance);

            return instance;
        }
    };

    private String deviceId;
    private RingDeviceFirmwareVersion version;
    private boolean wasSuccessful;

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

    public boolean isWasSuccessful() {
        return this.wasSuccessful;
    }

    public void setWasSuccessful(boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    private static final int classNameHashCode =
            internalHashCodeCompute("com.amazon.ata.executorservice.classroom.devicecommunication.UpdateDeviceFirmwareResponse");

    /**
     * HashCode implementation for UpdateDeviceFirmwareResponse
     * based on java.util.Arrays.hashCode
     */
    @Override
    public int hashCode() {
        return internalHashCodeCompute(
                classNameHashCode,
                getDeviceId(),
                getVersion(),
                isWasSuccessful());
    }

    private static int internalHashCodeCompute(Object... objects) {
        return Arrays.hashCode(objects);
    }

    /**
     * Equals implementation for UpdateDeviceFirmwareResponse
     * based on instanceof and Object.equals().
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof UpdateDeviceFirmwareResponse)) {
            return false;
        }

        UpdateDeviceFirmwareResponse that = (UpdateDeviceFirmwareResponse) other;

        return
                Objects.equals(getDeviceId(), that.getDeviceId())
                        && Objects.equals(getVersion(), that.getVersion())
                        && Objects.equals(isWasSuccessful(), that.isWasSuccessful());
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
        ret.append("UpdateDeviceFirmwareResponse(");

        ret.append("deviceId=");
        ret.append(String.valueOf(deviceId));
        ret.append(", ");

        ret.append("version=");
        ret.append(String.valueOf(version));
        ret.append(", ");

        ret.append("wasSuccessful=");
        ret.append(String.valueOf(wasSuccessful));
        ret.append(")");

        return ret.toString();
    }

}
