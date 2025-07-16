package com.example.hw13.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    @GetMapping("/hello")
    public ResponseEntity<Void> hello(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/anonymous")
    public String anonymousPage() {
        return "This is an anonymous page!";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "This is a public login page placeholder.";
    }

}
