package com.example.auser.yvtc1212exam2.firebase;

/**
 * Created by auser on 2017/12/12.
 */

public class ChatRoom {

    String fromName;
    String dateString;
    String message;

    public ChatRoom() {
    }

    public ChatRoom(String fromName, String dateString, String message) {
        this.fromName = fromName;
        this.dateString = dateString;
        this.message = message;
    }

    public String getFromName() {
        return fromName;
    }

    public String getDateString() {
        return dateString;
    }

    public String getMessage() {
        return message;
    }
}
