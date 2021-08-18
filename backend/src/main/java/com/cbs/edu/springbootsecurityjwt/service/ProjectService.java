package com.cbs.edu.springbootsecurityjwt.service;

import org.springframework.stereotype.Service;

import com.cbs.edu.springbootsecurityjwt.model.Project;
import com.cbs.edu.springbootsecurityjwt.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;

    public Iterable<Project> getAllProjects() {
        return repository.findAll();
    }
}
