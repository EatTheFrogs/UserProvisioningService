package com.eatthefrog.UserProvisioningService.controller;

import com.eatthefrog.UserProvisioningService.service.UserProvisioningService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
public class OktaProvisioningController {

    private final ObjectMapper objectMapper;
    private final UserProvisioningService userProvisioningService;

    private static final String OKTA_VERIFICATION_HEADER = "x-okta-verification-challenge";
    public DefaultSecurityFilterChain chain;

    @GetMapping("/create")
    public ResponseEntity<JsonNode> verifyOktaHook(@RequestHeader(OKTA_VERIFICATION_HEADER) String verificationString) {
        ObjectNode json = objectMapper.createObjectNode();
        json.put("verification", verificationString);
        return ResponseEntity.ok().body(json);
    }

    @PostMapping("/create")
    public ResponseEntity<String> provisionUser(@RequestBody String jsonString) {
        log.info("Okta User Provisioning Message received:\n"+jsonString);
        //userProvisioningService.publishOktaMessage();
        return ResponseEntity.ok("You are secured!");
    }
}
