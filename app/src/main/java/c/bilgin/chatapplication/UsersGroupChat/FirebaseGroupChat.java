package c.bilgin.chatapplication.UsersGroupChat;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import c.bilgin.chatapplication.ChatPage;
import c.bilgin.chatapplication.Firebase;
import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.UserOP.User;
import c.bilgin.chatapplication.UsersPersonalChat.Message;

public class FirebaseGroupChat  extends Firebase {
    public FirebaseGroupChat() {
        super("GroupChat", GroupChat.class);
    }

    GroupChat temp;
    @Override
    public void getData(final List someArr) {


        getDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp : dataSnapshot.getChildren()){
                    temp = (GroupChat)sp.getValue(getaClass());
                    List<User> users;
                    users = temp.getMembers();
                    for(User u:users)
                        if(u.getUID().equals(HomePage.currentUser.getUID()))
                            someArr.add(temp);
                }

                synchronized (someArr){
                    GroupChatFragment.adapter.notifyDataSetChanged();
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

    public void listenMessages(GroupChat p) {
        getDatabaseReference().child(p.getUid()).child("messages").limitToLast(1).addValueEventListener(new ValueEventListener() {
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
    public void sendMessage(GroupChat p,Message m){
        int element;
        if(p.getMessages()!=null)
            element = p.getMessages().size();
        else element = 0;
        getDatabaseReference().child(p.getUid()).child("messages").child(""+element).setValue(m);
        FirebaseDatabase.getInstance().getReference("messages").child(UUID.randomUUID().toString()).setValue(m);

    }
}
