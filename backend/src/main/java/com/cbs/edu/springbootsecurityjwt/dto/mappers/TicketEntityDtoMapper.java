package com.cbs.edu.springbootsecurityjwt.dto.mappers;

import com.cbs.edu.springbootsecurityjwt.dto.TicketDto;
import com.cbs.edu.springbootsecurityjwt.model.Ticket;

public class TicketEntityDtoMapper<ENTITY, DTO> implements EntityDtoMapper<Ticket, TicketDto> {

    @Override
    public TicketDto map(Ticket ticket) {
        return null;
    }
}
