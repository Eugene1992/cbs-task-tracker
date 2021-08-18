package com.cbs.edu.springbootsecurityjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.cbs.edu.springbootsecurityjwt.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
