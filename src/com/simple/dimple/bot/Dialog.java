package com.simple.dimple.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Dialog {
    private boolean needToCheck=false;
    private long versionId;
    private String lastmessege;
    private SendMessage nextMessege;
    private String subject;
    public void setText(SendMessage message)
    {
        nextMessege=message;
    }
    public void setId(long id){
        nextMessege.setChatId(String.valueOf(id));
    }
    public SendMessage getNextMessege()
    {
        return nextMessege;
    }
    public void setLastmessege(Message message){
        lastmessege=message.getText();

    }
    public void setSubject(String subject){
        this.subject=subject;
    }

    public String getSubject(){
        return subject;
    }


    public String getLastmessege() {
        if(lastmessege.isEmpty())
            return null;
        else
            return lastmessege;
    }

    public boolean isNeedToCheck() {
        return needToCheck;
    }
    public void setNeedToCheck(boolean wasVersionTaken){
        needToCheck=wasVersionTaken;

    }
}
