package com.cbs.edu.springbootsecurityjwt.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.edu.springbootsecurityjwt.model.User;
import com.cbs.edu.springbootsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/{userId}/tickets/{ticketId}/watch")
    public ResponseEntity<Void> watchTicket(@PathVariable Integer userId,
                                            @PathVariable Integer ticketId) {
        userService.watchTicket(userId, ticketId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/tickets/{ticketId}/stopWatch")
    public ResponseEntity<Void> stopWatchTicket(@PathVariable Integer userId,
                                            @PathVariable Integer ticketId) {
        userService.stopWatchTicket(userId, ticketId);
        return ResponseEntity.noContent().build();
    }
}
