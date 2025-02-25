package com.springlearn.backend.dto;

import lombok.Data;

import java.util.UUID;


@Data
public class HabitResponseDto {
    private UUID id;
    private String name;
    private String description;
    private UUID userId;
    private String frequency;

    // Конструктор для удобства
    public HabitResponseDto(UUID id, String name, String description, UUID userId, String frequency) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.frequency = frequency;
    }
}
