package com.cbs.edu.springbootsecurityjwt.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/async")
    public void async() throws InterruptedException {
        System.out.println("Start");
        doSomethingSlow();
        System.out.println("End");
    }

    @Async
    public void doSomethingSlow() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Async");
    }
}
