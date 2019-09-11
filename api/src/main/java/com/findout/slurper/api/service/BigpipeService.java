package com.findout.slurper.api.service;


import com.findout.slurper.api.domain.OrderData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BigpipeService {

    private RestTemplate restTemplate = new RestTemplate();
    private String serviceUrl;


    public BigpipeService(@Value("${server.bigpipe.url:http://localhost:7979}") String serviceUrl) {
        this.serviceUrl = serviceUrl + "/bigpipe/order";
    }

    public String sendOrder(OrderData order) {
        return restTemplate.postForObject(serviceUrl, order, String.class);
    }
}
