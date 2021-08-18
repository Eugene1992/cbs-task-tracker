package com.cbs.edu.springbootsecurityjwt.controller;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String login;

    private String password;
}
