package com.springlearn.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class HabitProgressDto {
    private UUID habitId;
    private LocalDateTime date;
    private boolean completed;
}
