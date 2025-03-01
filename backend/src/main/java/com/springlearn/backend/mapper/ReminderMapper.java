package com.springlearn.backend.mapper;

import com.springlearn.backend.dto.ReminderDto;
import com.springlearn.backend.model.Reminder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    @Mapping(source = "habit.id", target = "habitId")
    ReminderDto toDto(Reminder reminder);
}
