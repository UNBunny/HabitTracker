package com.springlearn.bot.service.command;


import com.springlearn.bot.client.BackendClient;
import com.springlearn.bot.dto.HabitDto;
import com.springlearn.bot.dto.UserDto;
import com.springlearn.bot.state.HabitCache;
import com.springlearn.bot.state.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AddHabitCommandHandler implements CommandHandler {

    @Autowired
    private BackendClient backendClient;
    @Autowired
    private HabitCache habitCache;
    private final Map<Long, UserState> userStateMap = new HashMap<>();


    @Override
    public boolean canHandle(String commandName) {
        return commandName.startsWith("/addHabit");
    }

    @Override
    public String handle(long chatId, String messageText) {
        userStateMap.put(chatId, UserState.AWAITING_HABIT_NAME);
        return "Введите название привычки:";
    }

    @Override
    public String handleUserInput(long chatId, String messageText) {
        UserState userState = userStateMap.getOrDefault(chatId, UserState.IDLE);

        switch (userState) {
            case AWAITING_HABIT_NAME:
                HabitDto habitDto = new HabitDto();
                habitDto.setName(messageText);
                habitCache.putHabitData(chatId, habitDto);
                userStateMap.put(chatId, UserState.AWAITING_HABIT_DESCRIPTION);
                return "Введите описание привычки: ";

            case AWAITING_HABIT_DESCRIPTION:
                HabitDto habitWithName = habitCache.getHabitData(chatId);
                habitWithName.setDescription(messageText);
                habitCache.putHabitData(chatId, habitWithName);
                userStateMap.put(chatId, UserState.AWAITING_HABIT_CATEGORY);
                return "Выберите категорию привычки:\n1: Здоровье\n2: Спорт\n3: Образование";
            case AWAITING_HABIT_CATEGORY:
                HabitDto habitWithNameAndDescription = habitCache.getHabitData(chatId);
                habitWithNameAndDescription.setCategoryId(Long.parseLong(messageText));
                habitCache.putHabitData(chatId, habitWithNameAndDescription);

                userStateMap.put(chatId, UserState.IDLE);
                habitCache.clearHabitData(chatId);

                return backendClient.addHabit(habitWithNameAndDescription);
            default:
                return "Неизвестная команда. Используй /start для начала.";

        }
    }
}
