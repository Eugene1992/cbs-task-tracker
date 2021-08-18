package com.cbs.edu.springbootsecurityjwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.model.Project;
import com.cbs.edu.springbootsecurityjwt.service.ComponentService;
import com.cbs.edu.springbootsecurityjwt.service.ProjectService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    public Iterable<Project> getAllProjects() {
        return service.getAllProjects();
    }
}
