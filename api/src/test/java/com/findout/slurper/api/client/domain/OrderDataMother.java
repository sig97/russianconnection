package com.findout.slurper.api.client.domain;


import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class OrderDataMother {

    private String orderId = UUID.randomUUID().toString();
    private Double price = ThreadLocalRandom.current().nextDouble(0, 1000);

    public OrderDataMother withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderDataMother withPrice(Double price) {
        this.price = price;
        return this;
    }

    public OrderData build() {
        return new OrderData(orderId, price);
    }
}

