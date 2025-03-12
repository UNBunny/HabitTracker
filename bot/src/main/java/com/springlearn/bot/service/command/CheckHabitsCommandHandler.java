package com.springlearn.bot.service.command;

import com.springlearn.bot.client.BackendClient;
import com.springlearn.bot.dto.HabitResponseDto;
import com.springlearn.bot.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CheckHabitsCommandHandler implements CommandHandler {

    private final BackendClient backendClient;

    @Override
    public String handle(long chatId, String messageText, String userName) {
        UserDto existingUser = backendClient.getUserByTelegramChatId(chatId);
        if (existingUser == null) {
            return "Пользователь не найден.";
        }

        List<HabitResponseDto> habits = backendClient.getHabitsByUser(existingUser.getId());

        StringBuilder response = new StringBuilder("Ваши привычки:\n");
        for (HabitResponseDto habit : habits) {
            response.append("🔹 *Название:* ").append(habit.getName()).append("\n")
                    .append("📖 *Описание:* ").append(habit.getDescription()).append("\n")
                    .append("📂 *Категория:* ").append(habit.getCategory()).append("\n")
                    .append("⏳ *Частота:* ").append(habit.getFrequency()).append("\n\n");
        }

        return response.toString();
    }

    @Override
    public String handleUserInput(long chatId, String messageText, String userName) {
        return "";
    }
}
