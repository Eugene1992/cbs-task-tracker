package com.cbs.edu.springbootsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cbs.edu.springbootsecurityjwt.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer>, JpaSpecificationExecutor {
}
