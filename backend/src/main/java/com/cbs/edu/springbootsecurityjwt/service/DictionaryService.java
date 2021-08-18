package com.cbs.edu.springbootsecurityjwt.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cbs.edu.springbootsecurityjwt.model.Label;
import com.cbs.edu.springbootsecurityjwt.model.Priority;
import com.cbs.edu.springbootsecurityjwt.model.Status;
import com.cbs.edu.springbootsecurityjwt.model.TicketType;
import com.cbs.edu.springbootsecurityjwt.repository.LabelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final CacheManager cacheManager;

    @Cacheable("dictionaries")
    public Iterable<?> getAllDictionaries(String name) {
        switch (name) {
            case "priority":
                return getPriorities();

            case "ticketType":
                return getTicketTypes();

            case "status":
                return getStatuses();
        }

        return new ArrayList<>();
    }

    public Iterable<?> getStatuses() {
        return Arrays.asList(Status.values());
    }

    public Iterable<?> getPriorities() {
/*        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return Arrays.asList(Priority.values());
    }

    public Iterable<?> getTicketTypes() {
/*        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return Arrays.asList(TicketType.values());
    }

    public void evictCache() {
//        cacheManager.getCache("dictionaries").invalidate();
    }

}
