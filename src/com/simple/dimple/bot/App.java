package com.simple.dimple.bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) throws IOException, TelegramApiException{
        Properties tgProps= new Properties();
        tgProps.load(App.class.getClassLoader().getResourceAsStream("tg.properties"));
        final Bot bot = new Bot("@simple_dimple_exam_bot","1814922061:AAG50huLS-2SZ7jtj6vqfo620Gr8hMj8aKk");
        TelegramBotsApi botsApi=new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);
    }
}
