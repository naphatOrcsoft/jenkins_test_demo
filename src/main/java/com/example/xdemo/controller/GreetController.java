package com.example.xdemo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.xdemo.service.GreetService;

@RestController
@RequestMapping("/api")
public class GreetController {
    @Autowired
    private GreetService greetService;

    @GetMapping("/greet")
    public String greet(@RequestParam(required=false) String name){
        return greetService.greet(name);
    }
}
