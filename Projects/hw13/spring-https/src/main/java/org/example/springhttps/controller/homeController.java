package org.example.springhttps.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {
    @GetMapping("/home")
    public String welcome() {
        return "Welcome to HTTPS page!";
    }

}
