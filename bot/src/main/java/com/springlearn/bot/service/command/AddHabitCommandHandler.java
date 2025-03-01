package com.springlearn.bot.service.command;


import com.springlearn.bot.client.BackendClient;
import com.springlearn.bot.dto.HabitRequestDto;
import com.springlearn.bot.dto.HabitResponseDto;
import com.springlearn.bot.dto.UserDto;
import com.springlearn.bot.state.HabitCache;
import com.springlearn.bot.state.UserState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
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
                HabitRequestDto habitRequestDto = new HabitRequestDto();
                habitRequestDto.setName(messageText);
                habitCache.putHabitData(chatId, habitRequestDto);
                userStateMap.put(chatId, UserState.AWAITING_HABIT_DESCRIPTION);
                return "Введите описание привычки: ";

            case AWAITING_HABIT_DESCRIPTION:
                HabitRequestDto habitWithName = habitCache.getHabitData(chatId);
                habitWithName.setDescription(messageText);
                habitCache.putHabitData(chatId, habitWithName);
                userStateMap.put(chatId, UserState.AWAITING_HABIT_CATEGORY);
                return "Выберите категорию привычки:\n1: Здоровье\n2: Спорт\n3: Образование";

            case AWAITING_HABIT_CATEGORY:
                HabitRequestDto habitWithNameAndDescription = habitCache.getHabitData(chatId);
                log.info("Получена категория: {}", messageText);
                try {
                    habitWithNameAndDescription.setCategoryId(Long.parseLong(messageText));
                } catch (NumberFormatException e) {
                    log.error("Ошибка при разборе категории: {}", messageText, e);
                    return "Ошибка! Введите номер категории (1, 2 или 3).";
                }
                habitCache.putHabitData(chatId, habitWithNameAndDescription);
                userStateMap.put(chatId, UserState.AWAITING_HABIT_FREQUENCY);

                return "Выберите частоту выполнения привычки:\nDAILY, WEEKLY OR MONTHLY";

            case AWAITING_HABIT_FREQUENCY:

                HabitRequestDto habitWithCategory = habitCache.getHabitData(chatId);

                habitWithCategory.setFrequency(messageText);

                UserDto userDto = backendClient.getUserByTelegramChatId(chatId);
                if (userDto == null || userDto.getId() == null) {
                    return "Ошибка: пользователь не найден в системе.";
                }
                habitWithCategory.setUserId(userDto.getId());
                HabitResponseDto savedHabit = backendClient.addHabit(habitWithCategory);
                // Очистка кэша
                userStateMap.put(chatId, UserState.IDLE);
                habitCache.clearHabitData(chatId);

//                log.debug("Пользователь добавил привычку: {}", habitWithCategory.getName());

                return formatHabitMessage(savedHabit);

            default:
                return "Неизвестная команда. Используй /start для начала.";
        }
    }

    private String formatHabitMessage(HabitResponseDto habit) {
//        log.debug(habit.toString());
        return String.format(
                "✅ *Привычка успешно добавлена!*\n\n" +
                        "📌 *Название:* %s\n" +
                        "📝 *Описание:* %s\n" +
                        "📂 *Категория:* %s\n" +
                        "⏳ *Частота:* %s",
                habit.getName(),
                habit.getDescription(),
                habit.getCategory(),// Получаем название категории
                habit.getFrequency()
        );
    }

}

