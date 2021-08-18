package com.cbs.edu.springbootsecurityjwt.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.edu.springbootsecurityjwt.service.DictionaryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dictionaries")
@RequiredArgsConstructor
@CrossOrigin
public class DictionaryController {

    private final DictionaryService service;

    @GetMapping("/{name}")
    public Iterable<?> getAllDictionaries(@PathVariable String name) {
        return service.getAllDictionaries(name);
    }

    @PostMapping("/cache/evict")
    @CacheEvict(value = "dictionaries", allEntries = true)
    public void evictCache() {
    }
}
