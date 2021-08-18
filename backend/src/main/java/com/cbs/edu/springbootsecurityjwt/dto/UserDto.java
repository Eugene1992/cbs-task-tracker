package com.cbs.edu.springbootsecurityjwt.dto;

import com.cbs.edu.springbootsecurityjwt.dto.mappers.EntityDtoMapper;
import com.cbs.edu.springbootsecurityjwt.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements EntityDtoMapper<User, UserDto> {
    private Integer id;
    private String firstName;
    private String lastName;

    @Override
    public UserDto map(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
