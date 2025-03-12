package com.springlearn.bot.service.command;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandHandlerFactory {
    private final Map<String, CommandHandler> handlers = new HashMap<>();

    public CommandHandlerFactory(StartCommandHandler startCommandHandler, AddHabitCommandHandler addHabitCommandHandler, CheckHabitsCommandHandler checkHabitsCommandHandler) {
        handlers.put("start", startCommandHandler);
        handlers.put("addhabit", addHabitCommandHandler);
        handlers.put("checkHabits", checkHabitsCommandHandler);
    }

    public CommandHandler getHandler(String commandName) {
        return handlers.getOrDefault(commandName, null);
    }

    public String unknownCommand(long chatId, String messageText) {
        return "Неизвестная комманда: " + messageText;
    }
}
