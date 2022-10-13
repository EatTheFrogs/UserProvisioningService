package com.eatthefrog.UserProvisioningService.controller;

import com.eatthefrog.UserProvisioningService.model.OktaRequest;
import com.eatthefrog.UserProvisioningService.service.UserProvisioningService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequiredArgsConstructor
public class OktaProvisioningController {

    private final UserProvisioningService userProvisioningService;

    private static final String OKTA_VERIFICATION_HEADER = "x-okta-verification-challenge";
    public DefaultSecurityFilterChain chain;

    @GetMapping("/create")
    public ResponseEntity<JsonNode> verifyOktaCreateHook(@RequestHeader(OKTA_VERIFICATION_HEADER) String verificationString) {
        return userProvisioningService.verifyOktaEndpoint(verificationString);
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody OktaRequest request) {
        log.info("Okta create message received:\n"+request.toString());
        return userProvisioningService.publishOktaCreateMessage(request);
    }

    @GetMapping("/delete")
    public ResponseEntity<JsonNode> verifyOktaDeleteHook(@RequestHeader(OKTA_VERIFICATION_HEADER) String verificationString) {
        return userProvisioningService.verifyOktaEndpoint(verificationString);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody OktaRequest request) {
        log.info("Okta delete message received:\n"+request.toString());
        return userProvisioningService.publishOktaDeleteMessage(request);
    }
}
