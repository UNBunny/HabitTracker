package com.springlearn.backend.mapper;

import com.springlearn.backend.dto.UserDto;
import com.springlearn.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = HabitMapper.class)
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}


