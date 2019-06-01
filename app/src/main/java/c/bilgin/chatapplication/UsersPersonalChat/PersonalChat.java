package c.bilgin.chatapplication.UsersPersonalChat;

import java.util.ArrayList;
import java.util.UUID;

public class PersonalChat {
    private String uuid;
    private User user1,user2;
    private ArrayList<Message> messages;
    //PersonalChat object should create a new personal chat beetween to users so sender receiver goes to message...

    public PersonalChat(User user1,User user2){
        this.uuid = UUID.randomUUID().toString();
        //find users according to their uuid or something from email name...
        this.user1 = user1;
        this.user2 = user2;
    }
    public void addMessageToPersonalChat(Message m){
        this.messages.add(m);
    }

    public PersonalChat createPersonalChat(User sender,User receiver,String message){
        Message m = new Message(sender,receiver,message);
        PersonalChat pc = new PersonalChat(sender,receiver);
        pc.addMessageToPersonalChat(m);

        return pc;
    }

    /*
    * send message and receive message properties
    * */
}
