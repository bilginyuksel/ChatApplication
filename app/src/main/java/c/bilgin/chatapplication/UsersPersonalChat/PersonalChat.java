package c.bilgin.chatapplication.UsersPersonalChat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import c.bilgin.chatapplication.UserOP.User;

public class PersonalChat {
    private String uuid,hostUID,guestUID;
    private Message lastMessage;
    private HashMap<String,Message> message;
    private User receiver,sender;
    //PersonalChat object should create a new personal chat beetween to users so sender receiver goes to message...

    public PersonalChat(String hostUID,String guestUID,User user1,User user2){

        //find users according to their uuid or something from email name...
        this.hostUID = hostUID;
        lastMessage = new Message();
        this.guestUID = guestUID;
        this.uuid = hostUID+guestUID;
        message = new HashMap<>();
        this.receiver = user1;
        this.sender = user2;
    }public PersonalChat(){}

    public HashMap<String, Message> getMessage() {
        return message;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setMessage(HashMap<String, Message> message) {
        this.message = message;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }



    /*
    * send message and receive message properties
    * */

    public void setGuestUID(String guestUID) {
        this.guestUID = guestUID;
    }

    public void setHostUID(String hostUID) {
        this.hostUID = hostUID;
    }

    public String getGuestUID() {
        return guestUID;
    }

    public String getHostUID() {
        return hostUID;
    }


    public void setUID(String uuid) {
        this.uuid = uuid;
    }

    public String getUID() {
        return uuid;
    }


}
