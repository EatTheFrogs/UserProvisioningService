package com.eatthefrog.UserProvisioningService.mq;

import com.eatthefrog.UserProvisioningService.model.OktaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServicePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue createQueue;
    private final Queue deleteQueue;

    public void publishCreateMessage(OktaRequest request) {
        rabbitTemplate.convertAndSend(createQueue.getName(), request);
    }

    public void publishDeleteMessage(OktaRequest request) {
        rabbitTemplate.convertAndSend(deleteQueue.getName(), request);
    }
}
