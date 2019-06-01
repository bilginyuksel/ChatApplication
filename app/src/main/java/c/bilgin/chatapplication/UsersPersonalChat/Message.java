package c.bilgin.chatapplication.UsersPersonalChat;

import java.util.UUID;

public class Message {
    private String uuid,message;
    private User sender,receiver;
    //basic Message 1 user sending 1 user receiving

    public Message(User sender,User receiver,String message){
        this.uuid = UUID.randomUUID().toString();
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
}
