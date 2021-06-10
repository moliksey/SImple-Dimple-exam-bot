package com.simple.dimple.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Dialog {
    private boolean needToCheck=false;
    private long versionId;
    private String lastmessege=new String();
    private String currentmessege;
    private SendMessage nextMessage;
    private SendPhoto phMessage=new SendPhoto();
    private SendDocument docMessage=new SendDocument();
    private String subject;
    private String ans;
    private boolean wasWrong=false;
    public void setText(SendMessage message)
    {
        nextMessage =message;
    }
    public void setId(long id){
        nextMessage.setChatId(String.valueOf(id));
        phMessage.setChatId(String.valueOf(id));
        docMessage.setChatId(String.valueOf(id));
    }
    public SendMessage getNextMessage()
    {
        return nextMessage;
    }
    public void setCurrentmessege(Message message){
        lastmessege=currentmessege;
        currentmessege =message.getText();

    }
    public void setSubject(String subject){
        this.subject=subject;
    }

    public String getSubject(){
        return subject;
    }


    public String getCurrentmessege() {
        if(currentmessege.isEmpty())
            return null;
        else
            return currentmessege;
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

    public void setPhMessage(InputFile ph) {
        this.phMessage.setPhoto(ph);
    }

    public SendDocument getDocMessage() {
        return docMessage;
    }

    public void setDocMessage(InputFile document) {
        this.docMessage.setDocument(document);
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public long getVersionId() {
        return versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    public String getLastmessege() {
        return lastmessege;
    }

    public boolean isItWasWrong() {
        return wasWrong;
    }

    public void setWasWrong(boolean wasWrong) {
        this.wasWrong = wasWrong;
    }
}
