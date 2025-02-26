package com.springlearn.bot.service.command;

public interface Command {
    String execute(long chatId, String messageText);
}
