package com.cbs.edu.springbootsecurityjwt.dto;

import com.cbs.edu.springbootsecurityjwt.dto.mappers.EntityDtoMapper;
import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponentDto implements EntityDtoMapper<Component, ComponentDto>  {

    private String name;

    @Override
    public ComponentDto map(Component component) {
        return ComponentDto.builder()
                .name(component.getName())
                .build();
    }
}
