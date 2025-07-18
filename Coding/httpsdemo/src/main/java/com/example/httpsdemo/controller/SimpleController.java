package com.example.httpsdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/api/hello")
    public ResponseEntity<Void> hello() {
        return ResponseEntity.ok().build();
    }
}