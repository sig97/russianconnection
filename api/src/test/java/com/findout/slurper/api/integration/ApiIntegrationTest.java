package com.findout.slurper.api.integration;

import com.findout.slurper.api.client.ApiClient;
import com.findout.slurper.api.client.domain.OrderData;
import com.findout.slurper.api.client.domain.OrderDataMother;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ApiIntegrationTest {

    private ApiClient apiClient = new ApiClient("http://localhost:7878");

    @Test
    public void pingTest() {
        assertEquals("OK", apiClient.ping());
    }

    @Test
    public void publishedOrderShouldBeForwarded() {
        OrderData order = new OrderDataMother().withOrderId("ID-2").build();

        apiClient.sendOrder(order);

        // TODO: check that it's forwarded
    }
}
