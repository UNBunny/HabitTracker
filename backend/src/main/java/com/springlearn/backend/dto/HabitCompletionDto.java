package com.springlearn.backend.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
// DTO для отметки выполнения привычки
public class HabitCompletionDto {

    @NotNull
    private UUID habitId;

    @NotNull
    private LocalDateTime date;

}
