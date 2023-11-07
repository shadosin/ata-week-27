package com.kenzie.executorservices.ringupdatescheck.model.customer;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;

public class GetCustomerDevicesResponse extends Object  {

    /**
     * Statically creates a builder instance for GetCustomerDevicesResponse.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Fluent builder for instances of GetCustomerDevicesResponse.
     */
    public static class Builder {

        protected String customerId;
        /**
         * Sets the value of the field "customerId" to be used for the constructed object.
         * @param customerId
         *   The value of the "customerId" field.
         * @return
         *   This builder.
         */
        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        protected List<String> deviceIds;
        /**
         * Sets the value of the field "deviceIds" to be used for the constructed object.
         * @param deviceIds
         *   The value of the "deviceIds" field.
         * @return
         *   This builder.
         */
        public Builder withDeviceIds(List<String> deviceIds) {
            this.deviceIds = deviceIds;
            return this;
        }

        /**
         * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
         * @param instance
         *   The instance to be populated.
         */
        protected void populate(GetCustomerDevicesResponse instance) {
            instance.setCustomerId(this.customerId);
            instance.setDeviceIds(this.deviceIds);
        }

        /**
         * Builds an instance of GetCustomerDevicesResponse.
         * <p>
         * The built object has its fields set to the values given when calling the "with*" methods of this builder.
         * </p>
         */
        public GetCustomerDevicesResponse build() {
            GetCustomerDevicesResponse instance = new GetCustomerDevicesResponse();

            populate(instance);

            return instance;
        }
    };

    private String customerId;
    private List<String> deviceIds;

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getDeviceIds() {
        return this.deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }

    private static final int classNameHashCode =
            internalHashCodeCompute("com.amazon.ata.executorservice.classroom.customer.GetCustomerDevicesResponse");

    /**
     * HashCode implementation for GetCustomerDevicesResponse
     * based on java.util.Arrays.hashCode
     */
    @Override
    public int hashCode() {
        return internalHashCodeCompute(
                classNameHashCode,
                getCustomerId(),
                getDeviceIds());
    }

    private static int internalHashCodeCompute(Object... objects) {
        return Arrays.hashCode(objects);
    }

    /**
     * Equals implementation for GetCustomerDevicesResponse
     * based on instanceof and Object.equals().
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GetCustomerDevicesResponse)) {
            return false;
        }

        GetCustomerDevicesResponse that = (GetCustomerDevicesResponse) other;

        return
                Objects.equals(getCustomerId(), that.getCustomerId())
                        && Objects.equals(getDeviceIds(), that.getDeviceIds());
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
        ret.append("GetCustomerDevicesResponse(");

        ret.append("customerId=");
        ret.append(String.valueOf(customerId));
        ret.append(", ");

        ret.append("deviceIds=");
        ret.append(String.valueOf(deviceIds));
        ret.append(")");

        return ret.toString();
    }

}
