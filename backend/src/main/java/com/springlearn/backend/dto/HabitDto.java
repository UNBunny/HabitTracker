package com.springlearn.backend.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class HabitDto {
    @NotNull(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Category ID is required")
    private UUID categoryId;

    @NotNull(message = "User ID is required")
    private UUID userId;

    private String frequency;
}