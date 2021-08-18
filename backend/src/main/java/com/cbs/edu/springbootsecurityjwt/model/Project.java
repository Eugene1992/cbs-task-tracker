package com.cbs.edu.springbootsecurityjwt.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cbs_projects")
public class Project extends AbstractEntity {
    private String name;

    public Project(Integer id) {
        super(id);
    }
}
