package com.springlearn.bot.service.command;

public interface CommandHandler {

//    boolean canHandle(String commandName);

    String handle(long chatId, String messageText, String userName);

    String handleUserInput(long chatId, String messageText, String userName);

}
