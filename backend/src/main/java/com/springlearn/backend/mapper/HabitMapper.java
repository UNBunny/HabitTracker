package com.springlearn.backend.mapper;

import com.springlearn.backend.dto.HabitRequestDto;
import com.springlearn.backend.dto.HabitResponseDto;
import com.springlearn.backend.model.Frequency;
import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.HabitCategory;
import com.springlearn.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HabitMapper {
    @Mapping(source = "category.name", target = "category") // Преобразуем категорию в строку
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "frequency", target = "frequency", qualifiedByName = "mapFrequency")
    HabitResponseDto toDto(Habit habit);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(source = "category", target = "category") // Используем метод ниже
    @Mapping(source = "user", target = "user")
    @Mapping(source = "dto.frequency", target = "frequency", qualifiedByName = "mapStringToFrequency")
    Habit toEntity(HabitRequestDto dto, User user, HabitCategory category);

    @Named("mapFrequency")
    static String mapFrequency(Frequency frequency) {
        return frequency != null ? frequency.name() : null;
    }

    @Named("mapStringToFrequency")
    static Frequency mapStringToFrequency(String frequency) {
        return Frequency.valueOf(frequency.toUpperCase());
    }

    // HabitCategory <-> String
    default HabitCategory map(String categoryName) {
        return categoryName != null ? new HabitCategory(null, categoryName, null) : null;
    }

    default String map(HabitCategory category) {
        return category != null ? category.getName() : null;
    }
}
