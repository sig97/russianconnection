package com.findout.slurper.api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderData {

    private final String orderId;
    private final Double price;
    private final String type;


    @JsonCreator
    public OrderData(@JsonProperty("orderId") String orderId,
                     @JsonProperty("price") Double price,
                     @JsonProperty("type") String type) {
        this.orderId = orderId;
        this.price = price;
        this.type = type;
    }

}
