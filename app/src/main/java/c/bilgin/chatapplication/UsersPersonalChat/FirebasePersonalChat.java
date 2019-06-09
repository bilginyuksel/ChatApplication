package c.bilgin.chatapplication.UsersPersonalChat;

import android.support.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.UUID;

import c.bilgin.chatapplication.ChatPage;
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
        int element;
        if(p.getMessages()!=null)
            element = p.getMessages().size();
        else element = 0;
        getDatabaseReference().child(p.getUID()).child("messages").child(""+element).setValue(m);
        FirebaseDatabase.getInstance().getReference("messages").child(UUID.randomUUID().toString()).setValue(m);
    }

    public void listenMessages(PersonalChat p) {
        getDatabaseReference().child(p.getUID()).child("messages").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp:dataSnapshot.getChildren()){
                    ChatPage.messages.add(sp.getValue(Message.class));
                }

                synchronized (ChatPage.messages){
                    ChatPage.chatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
