package com.springlearn.bot.service.command;

import com.springlearn.bot.client.BackendClient;
import com.springlearn.bot.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StartCommandHandler implements CommandHandler {

    @Autowired
    private BackendClient backendClient;

//    @Override
//    public boolean canHandle(String commandName) {
//        return commandName.startsWith("/start");
//    }

    @Override
    public String handle(long chatId, String messageText, String userName) {
        UserDto existingUser = backendClient.getUserByTelegramChatId(chatId);
        if (existingUser != null) {
            return "Добро пожаловать обратно, " + existingUser.getUserName() + "!";
        } else {
            UserDto newUser = new UserDto();
            newUser.setUserName(userName);
            newUser.setTelegramChatId(chatId);

            UserDto createdUser = backendClient.createUser(newUser);
            if (createdUser != null) {
                return "Добро пожаловать, " + createdUser.getUserName() + "! Ты успешно зарегистрирован.";
            } else {
                return "Ошибка при регистрации. Попробуй еще раз.";
            }
        }
    }

    @Override
    public String handleUserInput(long chatId, String messageText, String userName) {
        return "";
    }
}
