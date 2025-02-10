package com.demo_api.demo_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")

public class WelcomeController {

    @GetMapping
    public String welcome() {
        return "Welcome to the API GET";
    }

    @PostMapping
    public String other(){
        return "Welcome to the API POST";
    }

    

}