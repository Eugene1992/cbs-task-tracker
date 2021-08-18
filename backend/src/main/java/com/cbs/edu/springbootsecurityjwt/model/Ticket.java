package com.cbs.edu.springbootsecurityjwt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cbs_tickets")
public class Ticket extends AbstractEntity {

    private String key;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    private Long loggedHours;

    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    private TicketType type;

    private Integer estimate;

    @ManyToMany
    @JoinTable(name = "tickets_components",
            joinColumns = { @JoinColumn(name = "component_id")},
            inverseJoinColumns = { @JoinColumn(name = "ticket_id")})
    private List<Component> components;

    @ManyToMany
    @JoinTable(name = "tickets_labels",
            joinColumns = { @JoinColumn(name = "label_id")},
            inverseJoinColumns = { @JoinColumn(name = "ticket_id")})
    private List<Label> labels;

    @ManyToMany
    @JoinTable(name = "tickets_watchers",
            joinColumns = { @JoinColumn(name = "watcher_id")},
            inverseJoinColumns = { @JoinColumn(name = "ticket_id")})
    private List<User> watchers;
}
