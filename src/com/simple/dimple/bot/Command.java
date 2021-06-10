package com.simple.dimple.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
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
        String[] answers=dialog.getCurrentmessege().split(" ");
        String[] rightAnswers=dialog.getAns().split(" ");
        Set<String> mistakes=new HashSet<>();
        SendMessage message=new SendMessage();

        if(answers.length>1){
            for(int i=0;i<answers.length;i++)
            {
                if(!answers[i].split("\\.")[1].equals(rightAnswers[i].split("\\.")[1]))
                {
                    mistakes.add(answers[i].split("\\.")[0]);
                }
            }

        }
        else{
            if(!answers[0].equals(rightAnswers[0]))
            {
                mistakes.add(answers[0]);

            }
        }
        if (mistakes.size()==0){
            message.setText("You haven't made a single mistake");
            dialog.setText(message);
        }else{
            dialog.setWasWrong(true);
            if(answers.length==1){
                //sending right answer for this question
                message.setText("You wrong!");
                InputFile document= new InputFile(new File(""),String.valueOf(dialog.getVersionId()));
                dialog.setDocMessage(document);
                dialog.setText(message);
            }else{
                //sending right answer for all this questions
                message.setText("You wrong in "+String.valueOf(mistakes.size())+" tasks. Numbers of tasks you failed: "+mistakes.toString());
                InputFile document= new InputFile(new File(""),String.valueOf(dialog.getVersionId()));
                dialog.setDocMessage(document);
                dialog.setText(message);
            }
        }
        dialog.setNeedToCheck(false);
    }
}
class TakeExamVersionCommand implements Command{

    @Override
    public void execute(Dialog dialog) {
    //Generate and return exam version, you may add method in dialog class
        dialog.setVersionId(1l);//set here version id
        dialog.setAns(" ");//set answer here
        InputFile document= new InputFile(new File(""),String.valueOf(dialog.getVersionId()));
        dialog.setDocMessage(document);
        dialog.setNeedToCheck(true);

    }
}
class TakeTaskCommand implements Command{
    @Override
    public void execute(Dialog dialog) {
        String[] words=dialog.getCurrentmessege().split(" ");
        //Generate and return exam task, you may add method in dialog class, number of task in words[1]
        dialog.setVersionId(1l);//set here version id
        dialog.setAns(" ");//set answer here
        InputFile document= new InputFile(new File(""),String.valueOf(dialog.getVersionId()));
        dialog.setDocMessage(document);
        dialog.setNeedToCheck(true);
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
                "Now our bot can help with:"/* Math, RussianLanguage, Physics, */+" InformationTechnologies\n"+
        "\"/new\" - take you another task\n"+
                "\"/commands\" - the command you called, it shows the other commands");
        dialog.setText(message);
    }
}
class GetSubjectCommand implements Command{

    @Override
    public void execute(Dialog dialog) {
        Set<String> subjects=Set.of("RussianLanguage","Math","Physics","InformationTechnologies");
    String[] words=dialog.getCurrentmessege().split(" ");
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
