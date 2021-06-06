package com.simple.dimple.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Dialog {
    private boolean needToCheck=false;
    private long versionId;
    private String lastmessege;
    private SendMessage nextMessage;
    private SendPhoto phMessage;
    private SendDocument docMessage;
    private String subject;
    public void setText(SendMessage message)
    {
        nextMessage =message;
    }
    public void setId(long id){
        nextMessage.setChatId(String.valueOf(id));
    }
    public SendMessage getNextMessage()
    {
        return nextMessage;
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

    public SendPhoto getPhMessage() {
        return phMessage;
    }

    public void setPhMessage(SendPhoto phMessage) {
        this.phMessage = phMessage;
    }

    public SendDocument getDocMessage() {
        return docMessage;
    }

    public void setDocMessage(SendDocument docMessage) {
        this.docMessage = docMessage;
    }
}
