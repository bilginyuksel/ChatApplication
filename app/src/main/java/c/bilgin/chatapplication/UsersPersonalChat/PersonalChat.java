package c.bilgin.chatapplication.UsersPersonalChat;

import java.util.ArrayList;

import c.bilgin.chatapplication.UserOP.User;

public class PersonalChat {
    private String uuid,hostUID,guestUID;
    private ArrayList<Message> messages;
    private User receiver,sender;
    //PersonalChat object should create a new personal chat beetween to users so sender receiver goes to message...

    public PersonalChat(String hostUID,String guestUID,User user1,User user2){

        messages = new ArrayList<>();
        //find users according to their uuid or something from email name...
        this.hostUID = hostUID;
        this.guestUID = guestUID;
        this.uuid = hostUID+guestUID;
        this.receiver = user1;
        this.sender = user2;
    }public PersonalChat(){}
    public void sendMessage(Message m){
        this.messages.add(m);
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

    public PersonalChat createPersonalChat(String message){
        Message m = new Message(message,sender);
        PersonalChat pc = new PersonalChat(hostUID,guestUID,receiver,sender);
        pc.sendMessage(m);

        return pc;
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


    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void setUID(String uuid) {
        this.uuid = uuid;
    }

    public String getUID() {
        return uuid;
    }


    public ArrayList<Message> getMessages() {
        return messages;
    }
}
