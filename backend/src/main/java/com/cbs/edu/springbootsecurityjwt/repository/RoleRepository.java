package com.cbs.edu.springbootsecurityjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.cbs.edu.springbootsecurityjwt.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
