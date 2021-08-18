package com.cbs.edu.springbootsecurityjwt.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cbs.edu.springbootsecurityjwt.controller.request.TicketCreationRequest;
import com.cbs.edu.springbootsecurityjwt.controller.request.TicketSearchRequest;
import com.cbs.edu.springbootsecurityjwt.dto.TicketDto;
import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.model.Label;
import com.cbs.edu.springbootsecurityjwt.model.Priority;
import com.cbs.edu.springbootsecurityjwt.model.Project;
import com.cbs.edu.springbootsecurityjwt.model.Status;
import com.cbs.edu.springbootsecurityjwt.model.Ticket;
import com.cbs.edu.springbootsecurityjwt.model.TicketType;
import com.cbs.edu.springbootsecurityjwt.model.User;
import com.cbs.edu.springbootsecurityjwt.repository.TicketRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;
    @Lazy
    private final UserService userService;
    private final ComponentService componentService;
    private final LabelService labelService;
    private final WsNotificationService notificationService;

    public TicketDto getTicketDto(Integer id) {
        final Ticket ticket = repository.findById(id).get();

        return new TicketDto().map(ticket);
    }

    public Ticket getTicket(Integer id) {
        return repository.findById(id).get();
    }

    public Ticket updateTicket(Ticket ticket) {
        return repository.save(ticket);
    }

    public Ticket createTicket(TicketCreationRequest creationRequest) {
        Ticket newTicket = new Ticket();

        newTicket.setType(creationRequest.getType());
        newTicket.setTitle(creationRequest.getTitle());
        newTicket.setDescription(creationRequest.getDescription());
        newTicket.setPriority(creationRequest.getPriority());
        newTicket.setStatus(Status.OPEN);
        newTicket.setEstimate(creationRequest.getEstimate());

        Integer reporterId = creationRequest.getReporterId();
        User reporter = userService.getById(reporterId);
        newTicket.setReporter(reporter);

        Integer assigneeId = creationRequest.getAssigneeId();
        if (assigneeId != null) {
            User assignee = userService.getById(assigneeId);
            newTicket.setAssignee(assignee);
        }

        List<Component> components = componentService.getComponentByIds(creationRequest.getComponents());
        newTicket.setComponents(components);

        List<Label> labels = labelService.getLabelByIds(creationRequest.getLabels());
        newTicket.setLabels(labels);

        Ticket savedTicket = repository.save(newTicket);

//        notificationService.notifyUser(savedTicket.getAssignee().getId().toString(), "New ticket has been created for you!");

        return savedTicket;
    }

    public Integer getTicketWatchersCount(Integer id) {
        return getTicket(id).getWatchers().size();
    }

    @SuppressWarnings("unchecked")
    public Iterable<Ticket> searchTickets(TicketSearchRequest searchRequest) {
        return repository.findAll((Specification) (root, criteriaQuery, criteriaBuilder) -> {
            final Path<User> assigneePath = root.get("assignee");
            final Path<User> reporterPath = root.get("reporter");
            final Path<Project> projectPath = root.get("project");
            final Path<Priority> priorityPath = root.get("priority");
            final Path<Status> statusPath = root.get("status");
            final Path<TicketType> typePath = root.get("type");

            List<Predicate> predicates = new ArrayList<>();

            final Integer assigneeId = searchRequest.getAssigneeId();
            if (assigneeId != null) {
                predicates.add(criteriaBuilder.equal(assigneePath, new User(assigneeId)));
            }

            final Integer reporterId = searchRequest.getReporterId();
            if (reporterId != null) {
                predicates.add(criteriaBuilder.equal(reporterPath, new User(reporterId)));
            }

            final Integer projectId = searchRequest.getProjectId();
            if (projectId != null) {
                predicates.add(criteriaBuilder.equal(projectPath, new Project(projectId)));
            }

            final Priority priority = searchRequest.getPriority();
            if (priority != null) {
                predicates.add(criteriaBuilder.equal(priorityPath, priority));
            }

            final Status status = searchRequest.getStatus();
            if (status != null) {
                predicates.add(criteriaBuilder.equal(statusPath, status));
            }

            final TicketType ticketType = searchRequest.getType();
            if (ticketType != null) {
                predicates.add(criteriaBuilder.equal(typePath, ticketType));
            }

            return criteriaBuilder.and(predicates.stream()
                    .map(criteriaBuilder::and)
                    .toArray(Predicate[]::new));
        });
    }
}
