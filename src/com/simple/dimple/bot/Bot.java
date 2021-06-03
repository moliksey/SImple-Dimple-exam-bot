package com.simple.dimple.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;


public class Bot extends TelegramLongPollingBot {
    private final String name;
    private final String token;
    private final Map<Long, Dialog> dialogs;
    public Bot(String botName, String botToken) {
        super();
        this.name=botName;
        this.token=botToken;
        dialogs= new HashMap<>();
    }
    Map<String,Command> commands= new HashMap<>() {
        {
            put("start", new StartCommand());
            put("examversion", new TakeExamVersionCommand());
            put("", new TakeVersionCommand());
        }
    };
    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }



    @Override
    public void onUpdateReceived(Update update) {
        final Message message=update.getMessage();
        final long chatId=message.getChatId();
        final Dialog dialog=dialogs.computeIfAbsent(chatId, id -> new Dialog());
        commands.getOrDefault(message.getText(),d->{}).execute(dialog);
        SendMessage answer= new SendMessage();
        answer.setText("");
        answer.setChatId(String.valueOf(chatId));
        try{
            this.execute(answer);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}

