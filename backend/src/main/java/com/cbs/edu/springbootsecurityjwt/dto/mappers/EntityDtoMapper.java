package com.cbs.edu.springbootsecurityjwt.dto.mappers;

public interface EntityDtoMapper<ENTITY, DTO> {
    DTO map(ENTITY entity);
}
