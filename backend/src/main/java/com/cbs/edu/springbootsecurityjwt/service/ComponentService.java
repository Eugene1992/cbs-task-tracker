package com.cbs.edu.springbootsecurityjwt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.model.Label;
import com.cbs.edu.springbootsecurityjwt.repository.ComponentRepository;
import com.cbs.edu.springbootsecurityjwt.repository.LabelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComponentService {

    private final ComponentRepository repository;

    public Iterable<Component> getAllComponents() {
        return repository.findAll();
    }

    public List<Component> getComponentByIds(List<Integer> ids) {
        return (List<Component>) repository.findAllById(ids);
    }
}
