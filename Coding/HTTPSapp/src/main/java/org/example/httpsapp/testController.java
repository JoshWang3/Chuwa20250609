package org.example.httpsapp;


import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class testController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello There. The certificate is configured!";
    }

    //
    @GetMapping("/empty")
    public void empty() {

    }
}