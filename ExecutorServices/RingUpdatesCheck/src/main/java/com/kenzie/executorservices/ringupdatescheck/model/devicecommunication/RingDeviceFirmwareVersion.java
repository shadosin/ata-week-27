package com.kenzie.executorservices.ringupdatescheck.model.devicecommunication;

import java.util.Arrays;
import java.util.Objects;

public class RingDeviceFirmwareVersion extends Object  {

    /**
     * Statically creates a builder instance for RingDeviceFirmwareVersion.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Fluent builder for instances of RingDeviceFirmwareVersion.
     */
    public static class Builder {

        protected String versionNumber;
        /**
         * Sets the value of the field "versionNumber" to be used for the constructed object.
         * @param versionNumber
         *   The value of the "versionNumber" field.
         * @return
         *   This builder.
         */
        public Builder withVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
            return this;
        }

        /**
         * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
         * @param instance
         *   The instance to be populated.
         */
        protected void populate(RingDeviceFirmwareVersion instance) {
            instance.setVersionNumber(this.versionNumber);
        }

        /**
         * Builds an instance of RingDeviceFirmwareVersion.
         * <p>
         * The built object has its fields set to the values given when calling the "with*" methods of this builder.
         * </p>
         */
        public RingDeviceFirmwareVersion build() {
            RingDeviceFirmwareVersion instance = new RingDeviceFirmwareVersion();

            populate(instance);

            return instance;
        }
    };

    private String versionNumber;

    public String getVersionNumber() {
        return this.versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    private static final int classNameHashCode =
            internalHashCodeCompute("com.amazon.ata.executorservice.classroom.devicecommunication.RingDeviceFirmwareVersion");

    /**
     * HashCode implementation for RingDeviceFirmwareVersion
     * based on java.util.Arrays.hashCode
     */
    @Override
    public int hashCode() {
        return internalHashCodeCompute(
                classNameHashCode,
                getVersionNumber());
    }

    private static int internalHashCodeCompute(Object... objects) {
        return Arrays.hashCode(objects);
    }

    /**
     * Equals implementation for RingDeviceFirmwareVersion
     * based on instanceof and Object.equals().
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof RingDeviceFirmwareVersion)) {
            return false;
        }

        RingDeviceFirmwareVersion that = (RingDeviceFirmwareVersion) other;

        return
                Objects.equals(getVersionNumber(), that.getVersionNumber());
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
        ret.append("RingDeviceFirmwareVersion(");

        ret.append("versionNumber=");
        ret.append(String.valueOf(versionNumber));
        ret.append(")");

        return ret.toString();
    }

}
