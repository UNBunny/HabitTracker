package com.springlearn.bot.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String userName;
    private Long telegramChatId;
}
