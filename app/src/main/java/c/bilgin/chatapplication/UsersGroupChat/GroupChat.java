package c.bilgin.chatapplication.UsersGroupChat;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import c.bilgin.chatapplication.UserOP.User;
import c.bilgin.chatapplication.UsersPersonalChat.Message;

public class GroupChat {

    private List<User> members;
    private String uid,groupName,groupImage;
    private User groupLeader;
    private HashMap<String,Message> message;
    private Message lastMessage;

    public GroupChat(String groupName,String groupImage,User groupLeader,List<User> members){
        uid = UUID.randomUUID().toString();
        this.members = members;
        lastMessage = new Message();
        this.groupImage = groupImage;
        message = new HashMap<>();
        this.groupName = groupName;
        this.groupLeader = groupLeader;
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

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Message getLastMessage() {
        return lastMessage;
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

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public HashMap<String, Message> getMessage() {
        return message;
    }

    public void setMessage(HashMap<String, Message> message) {
        this.message = message;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public List<User> getMembers() {
        return members;
    }

    public String getUid() {
        return uid;
    }
}
