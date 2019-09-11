package com.findout.slurper.bigpipe.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderData {

    private final String orderId;
    private final Double price;


    @JsonCreator
    public OrderData(@JsonProperty("orderId") String orderId,
                     @JsonProperty("price") Double price) {
        this.orderId = orderId;
        this.price = price;
    }

}
