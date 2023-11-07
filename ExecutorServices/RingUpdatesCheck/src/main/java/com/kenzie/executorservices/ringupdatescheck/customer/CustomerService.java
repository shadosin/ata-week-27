package com.kenzie.executorservices.ringupdatescheck.customer;

import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesRequest;
import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mocks the RingCustomerService, allowing access to customer
 * data--in particular, including devices owned.
 *
 * Use the static method, getClient(), to obtain an instance to communicate
 * with the service.
 */
public class CustomerService {
    private static final int MIN_DEVICES = 2;
    private static final int MAX_INCREMENTAL_DEVICES = 17;

    private static final CustomerService SINGLETON = new CustomerService();

    private CustomerService() {}

    /**
     * Returns a client to use against the CustomerService.
     * @return a service client
     */
    public static CustomerService getClient() {
        return SINGLETON;
    }

    public GetCustomerDevicesResponse getCustomerDevices(final GetCustomerDevicesRequest request) {
        int rootDeviceId = Math.abs(Objects.hash(request.getCustomerId()));
        int numDevices = MIN_DEVICES + rootDeviceId % MAX_INCREMENTAL_DEVICES;
        List<String> deviceIds = new ArrayList<>(numDevices);

        for (int i = 0; i < numDevices; i++) {
            deviceIds.add(String.format("%d-%d", rootDeviceId, i));
        }

        return GetCustomerDevicesResponse.builder()
                .withCustomerId(request.getCustomerId())
                .withDeviceIds(deviceIds)
                .build();
    }
}
