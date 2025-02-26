package com.springlearn.bot.service;

import com.springlearn.bot.client.BackendClient;
import com.springlearn.bot.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BotService {

    private final BackendClient backendClient;

    public BotService(BackendClient backendClient) {
        this.backendClient = backendClient;
    }

    public String handleStartCommand(long chatId) {
        // Проверяем, зарегистрирован ли пользователь
        UserDto existingUser = backendClient.getUserByTelegramChatId(chatId);
        if (existingUser != null) {
            return "Добро пожаловать обратно, " + existingUser.getUserName() + "!";
        } else {
            // Создаем нового пользователя
            UserDto newUser = new UserDto();
            newUser.setUserName("User" + chatId); // Генерируем имя пользователя
            newUser.setTelegramChatId(chatId);

            UserDto createdUser = backendClient.createUser(newUser);
            if (createdUser != null) {
                return "Добро пожаловать, " + createdUser.getUserName() + "! Ты успешно зарегистрирован.";
            } else {
                return "Ошибка при регистрации. Попробуй еще раз.";
            }
        }
    }
}