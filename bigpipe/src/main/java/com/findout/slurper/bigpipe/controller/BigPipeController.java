package com.findout.slurper.bigpipe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.findout.slurper.bigpipe.configuration.RabbitConfig;
import com.findout.slurper.bigpipe.domain.OrderData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BigPipeController {

    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    public BigPipeController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/order")
    public @ResponseBody String receiveOrder(@RequestBody OrderData orderData) throws JsonProcessingException {

        String message = objectMapper.writeValueAsString(orderData);

        rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.queueName, "BIGPIPE>> Received message: " + message);
        return "{\"status\": \"ok\"}";
    }
}
