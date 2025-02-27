package com.springlearn.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitRequestDto {
    private String name;

    private String description;

    private Long categoryId;

    private UUID userId;

    //    private String frequency;

}