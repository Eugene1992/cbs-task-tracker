package com.cbs.edu.springbootsecurityjwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.service.ComponentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/components")
@RequiredArgsConstructor
@CrossOrigin
public class ComponentController {

    private final ComponentService service;

    @GetMapping
    public Iterable<Component> getAllComponents() {
        return service.getAllComponents();
    }
}
