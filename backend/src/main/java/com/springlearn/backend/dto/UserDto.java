package com.springlearn.backend.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String userName;
    private Long telegramChatId;
    private List<HabitResponseDto> habits;
}
