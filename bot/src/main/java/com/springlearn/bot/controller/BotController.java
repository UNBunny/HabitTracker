package com.springlearn.bot.controller;

import com.springlearn.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class BotController extends TelegramLongPollingBot {

    private final BotService botService;

    private final String botUsername = System.getenv("BOT_USERNAME");

    private final String botToken = System.getenv("BOT_TOKEN");


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            String userName = update.getMessage().getFrom().getUserName();

            String response = botService.handleCommand(chatId, messageText, userName);
            sendMessage(chatId, response);
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
