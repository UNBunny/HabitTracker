package com.springlearn.bot.dto;

import lombok.Data;

import java.util.UUID;


@Data
public class HabitResponseDto {
    private UUID id;
    private String name;
    private String description;
    private String category;
    private UUID userId;
    private String frequency;
}

