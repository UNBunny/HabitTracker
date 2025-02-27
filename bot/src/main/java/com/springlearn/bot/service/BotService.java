package com.springlearn.bot.service;

import com.springlearn.bot.service.command.CommandHandler;
import com.springlearn.bot.service.command.CommandHandlerFactory;
import com.springlearn.bot.state.UserState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BotService {

    private final CommandHandlerFactory commandHandlerFactory;
    private final Map<Long, UserState> userStates = new ConcurrentHashMap<>();

    public BotService(CommandHandlerFactory commandHandlerFactory) {
        this.commandHandlerFactory = commandHandlerFactory;
    }

    public String handleCommand(long chatId, String messageText) {
        // Проверяем состояние пользователя
        UserState currentState = userStates.getOrDefault(chatId, UserState.IDLE);

        if (currentState != UserState.IDLE) {
            // Если пользователь в процессе добавления привычки, передаем сообщение в обработчик
            CommandHandler handler = commandHandlerFactory.getHandler("addhabit");
            return handler.handleUserInput(chatId, messageText);
        } else {
            // Если состояние IDLE, обрабатываем команду
            String command = messageText.split(" ")[0].replaceFirst("/", "");
            CommandHandler handler = commandHandlerFactory.getHandler(command);

            if (handler != null) {
                // Устанавливаем состояние, если команда /addhabit
                if ("addhabit".equals(command)) {
                    userStates.put(chatId, UserState.AWAITING_HABIT_NAME);
                }
                return handler.handle(chatId, messageText);
            } else {
                return commandHandlerFactory.unknownCommand(chatId, messageText);
            }
        }
    }
}

