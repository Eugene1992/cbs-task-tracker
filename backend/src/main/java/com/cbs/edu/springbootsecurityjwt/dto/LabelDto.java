package com.cbs.edu.springbootsecurityjwt.dto;

import com.cbs.edu.springbootsecurityjwt.dto.mappers.EntityDtoMapper;
import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.model.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabelDto implements EntityDtoMapper<Label, LabelDto>  {

    private String name;

    @Override
    public LabelDto map(Label component) {
        return LabelDto.builder()
                .name(component.getName())
                .build();
    }
}
