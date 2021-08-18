package com.cbs.edu.springbootsecurityjwt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbs.edu.springbootsecurityjwt.model.Label;
import com.cbs.edu.springbootsecurityjwt.repository.LabelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository repository;

    public Iterable<Label> getAllLabels() {
        return repository.findAll();
    }

    public List<Label> getLabelByIds(List<Integer> ids) {
        return (List<Label>) repository.findAllById(ids);
    }
}
