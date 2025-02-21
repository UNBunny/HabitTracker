package com.springlearn.backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class HabitDto {
    private String name;
    private String description;
    private String categoryId;
    private UUID userId;
    private String frequency;
}
