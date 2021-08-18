package com.cbs.edu.springbootsecurityjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.cbs.edu.springbootsecurityjwt.model.Label;
import com.cbs.edu.springbootsecurityjwt.model.User;

public interface LabelRepository extends CrudRepository<Label, Integer> {
}
