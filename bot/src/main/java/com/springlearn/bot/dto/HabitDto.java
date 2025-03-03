package com.springlearn.bot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitDto {
    private String name;

    private String description;
    private Long categoryId;

    private UUID userId;

    private String frequency;
}
