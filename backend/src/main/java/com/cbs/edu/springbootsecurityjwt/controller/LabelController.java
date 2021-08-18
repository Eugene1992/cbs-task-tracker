package com.cbs.edu.springbootsecurityjwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.edu.springbootsecurityjwt.model.Label;
import com.cbs.edu.springbootsecurityjwt.service.LabelService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/labels")
@RequiredArgsConstructor
@CrossOrigin
public class LabelController {

    private final LabelService service;

    @GetMapping
    public Iterable<Label> getAllLabels() {
        return service.getAllLabels();
    }
}
