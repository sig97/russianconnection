package com.findout.slurper.api.service;

import org.springframework.stereotype.Component;

@Component
public class ApiQueueListener {


    // @RabbitListener(queues = {RabbitConfig.queueName})
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }

}
