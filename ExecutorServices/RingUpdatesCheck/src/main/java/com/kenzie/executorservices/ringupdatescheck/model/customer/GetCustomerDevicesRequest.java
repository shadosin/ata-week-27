package com.kenzie.executorservices.ringupdatescheck.model.customer;

import java.util.Arrays;
import java.util.Objects;

public class GetCustomerDevicesRequest extends Object  {

    /**
     * Statically creates a builder instance for GetCustomerDevicesRequest.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Fluent builder for instances of GetCustomerDevicesRequest.
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

        /**
         * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
         * @param instance
         *   The instance to be populated.
         */
        protected void populate(GetCustomerDevicesRequest instance) {
            instance.setCustomerId(this.customerId);
        }

        /**
         * Builds an instance of GetCustomerDevicesRequest.
         * <p>
         * The built object has its fields set to the values given when calling the "with*" methods of this builder.
         * </p>
         */
        public GetCustomerDevicesRequest build() {
            GetCustomerDevicesRequest instance = new GetCustomerDevicesRequest();

            populate(instance);

            return instance;
        }
    };

    private String customerId;

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    private static final int classNameHashCode =
            internalHashCodeCompute("com.amazon.ata.executorservice.classroom.customer.GetCustomerDevicesRequest");

    /**
     * HashCode implementation for GetCustomerDevicesRequest
     * based on java.util.Arrays.hashCode
     */
    @Override
    public int hashCode() {
        return internalHashCodeCompute(
                classNameHashCode,
                getCustomerId());
    }

    private static int internalHashCodeCompute(Object... objects) {
        return Arrays.hashCode(objects);
    }

    /**
     * Equals implementation for GetCustomerDevicesRequest
     * based on instanceof and Object.equals().
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof GetCustomerDevicesRequest)) {
            return false;
        }

        GetCustomerDevicesRequest that = (GetCustomerDevicesRequest) other;

        return
                Objects.equals(getCustomerId(), that.getCustomerId());
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
        ret.append("GetCustomerDevicesRequest(");

        ret.append("customerId=");
        ret.append(String.valueOf(customerId));
        ret.append(")");

        return ret.toString();
    }

}
