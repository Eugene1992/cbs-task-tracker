package com.cbs.edu.springbootsecurityjwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.edu.springbootsecurityjwt.config.aop.LogExecutionTime;
import com.cbs.edu.springbootsecurityjwt.controller.request.TicketCreationRequest;
import com.cbs.edu.springbootsecurityjwt.controller.request.TicketSearchRequest;
import com.cbs.edu.springbootsecurityjwt.dto.TicketDto;
import com.cbs.edu.springbootsecurityjwt.model.Ticket;
import com.cbs.edu.springbootsecurityjwt.service.TicketService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@CrossOrigin
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Integer id) {
        return ticketService.getTicketDto(id);
    }

    @GetMapping("/{id}/watchers/count")
    public Integer getTicketWatchersCount(@PathVariable Integer id) {
        return ticketService.getTicketWatchersCount(id);
    }

    @PostMapping
    public Ticket createTicket(@RequestBody TicketCreationRequest creationRequest) {
        return ticketService.createTicket(creationRequest);
    }

    @PostMapping("/search")
    @ApiOperation(value = "Search tickets by provided filters", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @LogExecutionTime
    public Iterable<Ticket> searchTickets(@RequestBody TicketSearchRequest searchRequest) {
        return ticketService.searchTickets(searchRequest);
    }
}
