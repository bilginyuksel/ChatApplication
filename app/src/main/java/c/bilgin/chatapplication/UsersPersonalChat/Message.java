package c.bilgin.chatapplication.UsersPersonalChat;

import java.util.Date;
import java.util.UUID;

import c.bilgin.chatapplication.UserOP.User;

public class Message {
    private String uuid,message;
    private User sender;
    private long message_date;
    //basic Message 1 user sending 1 user receiving
    //Add date to messages
    /*
    * Date message_sent; maybe received too
    * */

    public Message(String message,User sender){
        this.uuid = UUID.randomUUID().toString();
        this.message = message;
        this.sender = sender;
        message_date = new Date().getTime();
    }public Message(){}

    public long getMessage_date() {
        return message_date;
    }

    public void setMessage_date(long message_date) {
        this.message_date = message_date;
    }

    public String getMessage() {
        return message;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getSender() {
        return sender;
    }




    public String getUuid() {
        return uuid;
    }
}
