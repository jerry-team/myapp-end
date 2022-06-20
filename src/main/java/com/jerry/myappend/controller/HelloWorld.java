package com.jerry.myappend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {
    @RequestMapping("/helloworld")
    public String hello(){
        return "Hello world!!!";
    }
}
