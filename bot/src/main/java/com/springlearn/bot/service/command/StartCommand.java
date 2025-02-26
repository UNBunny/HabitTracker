package com.springlearn.bot.service.command;

public class StartCommand implements Command {
    @Override
    public String execute(long chatId, String messageText) {
        return "Привет! Я помогу тебе отслеживать привычки. Используй /addhabit чтобы добавить привычку.";
    }
}
