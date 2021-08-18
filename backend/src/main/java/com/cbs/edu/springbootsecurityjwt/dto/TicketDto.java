package com.cbs.edu.springbootsecurityjwt.dto;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.util.CollectionUtils;

import com.cbs.edu.springbootsecurityjwt.dto.mappers.EntityDtoMapper;
import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.model.Label;
import com.cbs.edu.springbootsecurityjwt.model.Priority;
import com.cbs.edu.springbootsecurityjwt.model.Status;
import com.cbs.edu.springbootsecurityjwt.model.Ticket;
import com.cbs.edu.springbootsecurityjwt.model.TicketType;
import com.cbs.edu.springbootsecurityjwt.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto implements EntityDtoMapper<Ticket, TicketDto> {
    private Integer id;
    private String key;
    private String title;
    private String description;
    private UserDto assignee;
    private UserDto reporter;
    private Long loggedHours;
    private Priority priority;
    private Status status;
    private TicketType type;
    private List<ComponentDto> components;
    private List<LabelDto> labels;
    private List<UserDto> watchers;

    @Override
    public TicketDto map(Ticket ticket) {
        TicketDtoBuilder builder = TicketDto.builder()
                .id(ticket.getId())
                .key(ticket.getKey())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .loggedHours(ticket.getLoggedHours())
                .priority(ticket.getPriority())
                .type(ticket.getType())
                .status(ticket.getStatus());

        User assignee = ticket.getAssignee();
        if (nonNull(assignee)) {
            builder.assignee(new UserDto().map(assignee));
        }

        User reporter = ticket.getReporter();
        if (nonNull(reporter)) {
            builder.reporter(new UserDto().map(reporter));
        }

        List<Component> components = ticket.getComponents();
        if (!CollectionUtils.isEmpty(components)) {
            builder.components(components.stream()
                    .map(component -> new ComponentDto().map(component))
                    .collect(Collectors.toList()));
        }

        List<Label> labels = ticket.getLabels();
        if (!CollectionUtils.isEmpty(labels)) {
            builder.labels(labels.stream()
                    .map(label -> new LabelDto().map(label))
                    .collect(Collectors.toList()));
        }

        List<User> watchers = ticket.getWatchers();
        if (!CollectionUtils.isEmpty(watchers)) {
            builder.watchers(watchers.stream()
                    .map(user -> new UserDto().map(user))
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }
}
