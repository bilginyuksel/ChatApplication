package c.bilgin.chatapplication.UsersGroupChat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import c.bilgin.chatapplication.UserOP.User;
import c.bilgin.chatapplication.UsersPersonalChat.Message;

public class GroupChat {

    private List<User> members;
    private String uid,groupName,groupImage;
    private User groupLeader;
    private List<Message> messages;

    public GroupChat(String groupName,String groupImage,User groupLeader,List<User> members){
        uid = UUID.randomUUID().toString();
        this.members = members;
        this.groupImage = groupImage;
        this.groupName = groupName;
        this.groupLeader = groupLeader;
        messages = new ArrayList<>();
    }public GroupChat(){}

    public void setGroupLeader(User groupLeader) {
        this.groupLeader = groupLeader;
    }

    public User getGroupLeader() {
        return groupLeader;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void sendMessage(Message m){
        this.messages.add(m);
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<User> getMembers() {
        return members;
    }

    public String getUid() {
        return uid;
    }
}
