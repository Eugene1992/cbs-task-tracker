package com.cbs.edu.springbootsecurityjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
