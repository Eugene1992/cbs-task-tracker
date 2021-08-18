package com.cbs.edu.springbootsecurityjwt.controller.request;

import com.cbs.edu.springbootsecurityjwt.model.Priority;
import com.cbs.edu.springbootsecurityjwt.model.Status;
import com.cbs.edu.springbootsecurityjwt.model.TicketType;
import lombok.Data;

@Data
public class TicketSearchRequest {
    private Integer projectId;
    private Integer assigneeId;
    private Integer reporterId;
    private Priority priority;
    private TicketType type;
    private Status status;
}
