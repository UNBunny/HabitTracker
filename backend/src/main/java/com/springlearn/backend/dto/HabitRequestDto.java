package com.springlearn.backend.dto;

import com.springlearn.backend.model.Frequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitRequestDto {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Long categoryId;

    @NotNull
    private UUID userId;

    @NotNull
    private String frequency; // В ЭТОМ СЛУЧАЕ ПРИХОДИТ ИЗ JSON, ПОЭТОМУ STRING!!!!!


}