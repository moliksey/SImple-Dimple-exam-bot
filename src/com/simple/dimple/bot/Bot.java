package com.simple.dimple.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
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



    @Override
    public void onUpdateReceived(Update update) {
        final Message message=update.getMessage();
        final long chatId=message.getChatId();
        final Dialog dialog=dialogs.computeIfAbsent(chatId, id -> new Dialog());
        dialog.setLastmessege(message);
        String[] words=message.getText().split(" ");
        if(dialog.isNeedToCheck()){
            new CheckAnswerCommand().execute(dialog);
        }
        else{
        commands.getOrDefault(words[0],d->{}).execute(dialog);
        }
        dialog.setId(chatId);
        try{
            if(!(words[0].equals("/examversion")||words[0].equals("/taskversion")))
                this.execute(dialog.getNextMessage());
            else{
                if(words[0].equals("/examversion")){
                    this.execute(dialog.getDocMessage());
                }
                else{
                this.execute(dialog.getPhMessage());}}
        }catch (TelegramApiException e){
            e.printStackTrace();
        }

    }
}

