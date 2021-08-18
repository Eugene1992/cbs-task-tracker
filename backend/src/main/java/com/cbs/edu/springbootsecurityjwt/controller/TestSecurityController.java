package com.cbs.edu.springbootsecurityjwt.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    @GetMapping("/admin/get")
    public String getAdmin() {
        return "Hi admin";
    }

    @GetMapping("/user/get")
    public String getUser() {
        return "Hi user";
    }

//    @Secured("ROLE_ADMIN")
//    @RolesAllowed("ROLE_ADMIN")

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")

    @GetMapping("/preAuthorize")
    public String testPreAuthorize() {
        return "Hi admin";
    }
}
