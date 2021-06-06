package com.simple.dimple.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashSet;
import java.util.Set;

public interface Command {
    void execute(Dialog dialog);
}
class StartCommand implements Command{

    @Override
    public void execute(Dialog dialog) {
        SendMessage message=new SendMessage();
        message.setText("Hello! \nChoose subject, and let's start learning!\nWrite /commands to see all commands");
    dialog.setText(message);
    }
}
class CheckAnswerCommand implements Command{

    @Override
    public void execute(Dialog dialog) {
        SendMessage message=new SendMessage();
        message.setText("Checked");
        dialog.setText(message);
    }
}
class TakeExamVersionCommand implements Command{

    @Override
    public void execute(Dialog dialog) {
    //Generate and return exam version, you may add method in dialog class
    }
}
class TakeTaskCommand implements Command{
    @Override
    public void execute(Dialog dialog) {
        String[] words=dialog.getLastmessege().split(" ");
        //Generate and return exam task, you may add method in dialog class, number of task in words[1]
    }
}
class Commands implements Command{
    @Override
    public void execute(Dialog dialog) {
        SendMessage message=new SendMessage();
        message.setText("\"/start\" - our bot is pleased when you say hello to him.\n"+
        "\"/examversion\" - takes one of our exam options and sends it to you," +
                " then you should answer in one message, all answers have to be looking like:" +
                "\"[number of task].[answer in lower case, in one word]\".\n"+
        "\"/taskversion [Task number]\" - takes one of our exam tasks and sends it to you.\n"+
        "\"/subject [Subject name]\" - there you can choose subject you want to studiing.\n" +
                "Now our bot can help with: Math, RussianLanguage, Physics, InformationTechnologies\n"+
        "\"/commands\" - the command you called, it shows the other commands");
        dialog.setText(message);
    }
}
class GetSubjectCommand implements Command{

    @Override
    public void execute(Dialog dialog) {
        Set<String> subjects=Set.of("RussianLanguage","Math","Physics","InformationTechnologies");
    String[] words=dialog.getLastmessege().split(" ");
        SendMessage message=new SendMessage();
    if(subjects.contains(words[1]))
    {
        dialog.setSubject(words[1]);
        message.setText("Your subject was successfully accepted. You can change subject in any time." +
                        "\nThen you want full exam version or just work through only one task?");
    }
    else {
        message.setText("What? It's impossible but it's looks like we have an error.\nTry again.");
    }
        dialog.setText(message);
    }
}
