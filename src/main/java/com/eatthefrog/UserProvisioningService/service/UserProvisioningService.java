package com.eatthefrog.UserProvisioningService.service;

import com.eatthefrog.UserProvisioningService.model.*;
import com.eatthefrog.UserProvisioningService.mq.UserServicePublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserProvisioningService {

    private final ObjectMapper objectMapper;
    private final UserServicePublisher userServicePublisher;

    public ResponseEntity verifyOktaEndpoint(String verificationString) {
        ObjectNode json = objectMapper.createObjectNode();
        json.put("verification", verificationString);
        return ResponseEntity.ok().body(json);
    }

    public ResponseEntity publishOktaCreateMessage(OktaRequest request) {
        userServicePublisher.publishCreateMessage(request);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity publishOktaDeleteMessage(OktaRequest request) {
        userServicePublisher.publishDeleteMessage(request);
        return ResponseEntity.ok().build();
    }
}
