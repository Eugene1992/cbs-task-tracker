package com.cbs.edu.springbootsecurityjwt.controller.request;

import java.util.List;

import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.model.Label;
import com.cbs.edu.springbootsecurityjwt.model.Priority;
import com.cbs.edu.springbootsecurityjwt.model.TicketType;
import com.cbs.edu.springbootsecurityjwt.model.User;
import lombok.Data;

@Data
public class TicketCreationRequest {
    private String title;
    private String description;
    private Integer assigneeId;
    private Integer reporterId;
    private Priority priority;
    private TicketType type;
    private List<Integer> components;
    private List<Integer> labels;
    private Integer estimate;
}
