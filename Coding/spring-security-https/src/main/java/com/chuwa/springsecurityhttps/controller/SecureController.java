package com.chuwa.springsecurityhttps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure")
    public ResponseEntity<String> secureEndpoint() {
        return ResponseEntity.ok("");
    }

    @GetMapping("/")
    public ResponseEntity<String> homeEndpoint() {
        return ResponseEntity.ok("HTTPS Application is running!");
    }
} 