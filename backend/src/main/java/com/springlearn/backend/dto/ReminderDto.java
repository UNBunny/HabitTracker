package com.springlearn.backend.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class ReminderDto {
    private UUID habitId;
    private LocalTime time;
    private boolean isSent;
}
