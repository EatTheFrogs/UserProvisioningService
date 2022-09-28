package com.eatthefrog.UserProvisioningService.service;

import com.eatthefrog.UserProvisioningService.mq.UserServicePublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserProvisioningService {

    private final UserServicePublisher userServicePublisher;

    public void publishOktaMessage() {
        userServicePublisher.publishMessage();
    }
}
