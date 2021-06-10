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
    private static final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11417745";
    private static final String user = "sql11417745";
    private static final String password = "eIMih4pNNj";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) throws IOException, TelegramApiException{
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            //try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            //try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        Properties tgProps= new Properties();
        tgProps.load(App.class.getClassLoader().getResourceAsStream("tg.properties"));
        final Bot bot = new Bot("@simple_dimple_exam_bot","1814922061:AAG50huLS-2SZ7jtj6vqfo620Gr8hMj8aKk");
        TelegramBotsApi botsApi=new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);
    }
}
