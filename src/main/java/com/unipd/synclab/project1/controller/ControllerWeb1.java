package com.unipd.synclab.project1.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ControllerWeb1 {
    @GetMapping("")
    public String hello(){
        return "hello world";
    }

    @GetMapping("user")
    public String getUser(@RequestParam Integer userId) {
        return "l'utente " + userId + " si chiama Marco";
    }
    
}
