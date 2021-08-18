package com.cbs.edu.springbootsecurityjwt.service;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cbs.edu.springbootsecurityjwt.dto.TicketDto;
import com.cbs.edu.springbootsecurityjwt.model.Ticket;
import com.cbs.edu.springbootsecurityjwt.model.User;
import com.cbs.edu.springbootsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private TicketService ticketService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User saveUser(User userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public User findByLogin(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void watchTicket(Integer userId, Integer ticketId) {
        final Ticket ticket = ticketService.getTicket(ticketId);
        final List<User> watchers = ticket.getWatchers();

        if (nonNull(watchers)) {
            Set<Integer> watcherIds = watchers.stream()
                    .map(User::getId)
                    .collect(Collectors.toSet());

            if (!watcherIds.contains(userId)) {
                final User watcher = getById(userId);
                watchers.add(watcher);
                ticketService.updateTicket(ticket);
            }
        }
    }

    public void stopWatchTicket(Integer userId, Integer ticketId) {
        final Ticket ticket = ticketService.getTicket(ticketId);
        final List<User> watchers = ticket.getWatchers();

        if (!isEmpty(watchers)) {
            List<User> filteredWatchers = watchers.stream()
                    .filter(watcher -> !watcher.getId().equals(userId))
                    .collect(Collectors.toList());
            ticket.setWatchers(filteredWatchers);

            ticketService.updateTicket(ticket);
        }
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
