package com.springlearn.backend.mapper;

import com.springlearn.backend.dto.HabitProgressDto;
import com.springlearn.backend.model.HabitProgress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HabitProgressMapper {
    @Mapping(source = "habit.id", target = "habitId")
    HabitProgressDto toDto(HabitProgress habitProgress);
}
