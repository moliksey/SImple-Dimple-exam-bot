package com.simple.dimple.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
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
            put("/start", new StartCommand());
            put("/examversion", new TakeExamVersionCommand());
            put("/taskversion", new TakeTaskCommand());
            put("/subject", new GetSubjectCommand());
            put("/commands", new Commands());
        }
    };
    @Override
    public String getBotUsername() {
        return this.name;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }


   /* public void sendPhoto(SendPhoto photo){
        try{
            this.execute(photo);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void sendDocument(SendDocument document){
        try{
            this.execute(document);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }*/

    @Override
    public void onUpdateReceived(Update update) {
        final Message message=update.getMessage();
        final long chatId=message.getChatId();
        final Dialog dialog=dialogs.computeIfAbsent(chatId, id -> new Dialog());
        dialog.setCurrentmessege(message);
        String[] words;
        if(message.getText().equals("/new")) {
            words=dialog.getLastmessege().split(" ");
            commands.getOrDefault(words[0], d -> {
            }).execute(dialog);
        }else{
            words=message.getText().split(" ");
            commands.getOrDefault(words[0],d->{}).execute(dialog);
            if(dialog.isNeedToCheck()){
                new CheckAnswerCommand().execute(dialog);
            }
        }
        dialog.setId(chatId);
        try{
            if(!(words[0].equals("/examversion")||words[0].equals("/taskversion")||words[0].equals("/new")))
            {
                this.execute(dialog.getNextMessage());
                if(dialog.isItWasWrong())
                {
                    this.execute(dialog.getDocMessage());
                    dialog.setWasWrong(false);
                }
            }
            else{
                    this.execute(dialog.getDocMessage());
            }
        }catch (TelegramApiException e){
            e.printStackTrace();
        }

    }
}

