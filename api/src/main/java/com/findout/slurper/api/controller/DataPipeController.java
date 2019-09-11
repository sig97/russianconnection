package com.findout.slurper.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.findout.slurper.api.configuration.RabbitConfig;
import com.findout.slurper.api.domain.OrderData;
import com.findout.slurper.api.service.BigpipeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataPipeController {

    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;
    private BigpipeService bigpipeService;

    public DataPipeController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, BigpipeService bigpipeService) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.bigpipeService = bigpipeService;
    }

    @PostMapping("/order")
    public @ResponseBody String sendOrder(@RequestBody OrderData orderData) throws JsonProcessingException {

        String message = objectMapper.writeValueAsString(orderData);

        rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, RabbitConfig.queueName, "API>> Received message: " + message);

        String response = bigpipeService.sendOrder(orderData);

        return String.format("{\"responseString\": \"%s\"}", response);
    }
}
