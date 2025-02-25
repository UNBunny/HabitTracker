package com.springlearn.backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id; // Нужно убрать ID, для этого переделываем UserService (по хорошему все переделать на MapStruct)
    private String userName;
    private Long telegramChatId;
}
