package c.bilgin.chatapplication.UsersPersonalChat;

import android.support.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import c.bilgin.chatapplication.ChatOperations.ChatPage;
import c.bilgin.chatapplication.Firebase;
import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.UserOP.User;

public class FirebasePersonalChat extends Firebase {
    public FirebasePersonalChat() {
        super("PersonalChat", PersonalChat.class);
    }
    private PersonalChat pc;
    private User temp;


    @Override
    public void getData(final List someArr) {

        //you can use static arrays for it..
        //protected static
        someArr.clear();



        getDatabaseReference().orderByChild("guestUID").equalTo(HomePage.currentUser.getUID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp:dataSnapshot.getChildren()) {
                    pc = (PersonalChat) sp.getValue(getaClass());
                    temp = pc.getReceiver();
                    if(temp.getUID().equals(HomePage.currentUser.getUID())){
                        pc.setReceiver(pc.getSender());
                    }
                    else pc.setReceiver(temp);
                    HashMap<String,Message> m = new HashMap<>();
                    for(DataSnapshot sp1 : sp.child("messages").getChildren()){
                        m.put(sp1.getKey(),sp1.getValue(Message.class));
                    }
                    pc.setMessage(m);
                    someArr.add(pc);
                }

                synchronized (someArr){
                    PersonalChatFragment.personalChatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        getDatabaseReference().orderByChild("hostUID").equalTo(HomePage.currentUser.getUID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp:dataSnapshot.getChildren()){
                    pc = (PersonalChat) sp.getValue(getaClass());
                    temp = pc.getReceiver();
                    if(temp.getUID().equals(HomePage.currentUser.getUID())){
                        pc.setReceiver(pc.getSender());
                    }
                    else pc.setReceiver(temp);

                    HashMap<String,Message> m = new HashMap<>();
                    for(DataSnapshot sp1 : sp.child("messages").getChildren()){
                        m.put(sp1.getKey(),sp1.getValue(Message.class));
                    }
                    pc.setMessage(m);
                    someArr.add(pc);
                }

                synchronized (someArr){
                    PersonalChatFragment.personalChatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void addData(Object o, String uid) {
        getDatabaseReference().child(uid).setValue(o);
    }

    @Override
    public void updateData() {

    }
    public void sendMessage(PersonalChat p,Message m){
        getDatabaseReference().child(p.getUID()).child("messages").push().setValue(m);
        getDatabaseReference().child(p.getUID()).child("lastMessage").setValue(m);
        p.setLastMessage(m);
        addMessageForNotification(p,m);
    }

    private void addMessageForNotification(PersonalChat p,Message m){
        HashMap<String,String> editedMessage = new HashMap<>();
        editedMessage.put("message",m.getMessage());
        editedMessage.put("sender_id",m.getSender().getUID());
        editedMessage.put("receiver_id",p.getReceiver().getUID());
        editedMessage.put("name",m.getSender().getName());
        FirebaseDatabase.getInstance().getReference("messages").child(UUID.randomUUID().toString()).setValue(editedMessage);
    }

    public void listenMessages(PersonalChat p) {
        //limit to last 20 if you wanted
        getDatabaseReference().child(p.getUID()).child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ChatPage.messages.clear();
                for(DataSnapshot sp:dataSnapshot.getChildren()){
                    p.getMessage().put(sp.getKey(),sp.getValue(Message.class));
                    ChatPage.messages.add(sp.getValue(Message.class));
                }

                synchronized (ChatPage.messages){
                    if(ChatPage.messages.size()>0) p.setLastMessage(ChatPage.messages.get(ChatPage.messages.size()-1));
                    ChatPage.chatPersonalAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
