package c.bilgin.chatapplication.UsersGroupChat;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

import c.bilgin.chatapplication.ChatOperations.ChatPage;
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
                    HashMap<String,Message> m = new HashMap<>();
                    for(DataSnapshot sp1 :sp.child("messages").getChildren()){
                        m.put(sp1.getKey(),sp1.getValue(Message.class));
                    }
                    temp.setMessage(m);
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

    public void listenMessages(final GroupChat p) {
        //Fetching all messages always we have to figure out this problem
        //if there was serializable list or something else we could limitToFirst or last...
        //Think about it maybe hashMaps the solution but not the messageuid with push command.


        //you can use limit tolaast function..
        getDatabaseReference().child(p.getUid()).child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ChatPage.msgAdap.clear();
                for(DataSnapshot sp:dataSnapshot.getChildren()){
                    p.getMessage().put(sp.getKey(),sp.getValue(Message.class));
                    ChatPage.msgAdap.add(sp.getValue(Message.class));
                }
                //ChatPage.receiveNewMessage(p.getMessage());

                synchronized (ChatPage.msgAdap){
                    if(ChatPage.msgAdap.size()>0) p.setLastMessage(ChatPage.msgAdap.get(ChatPage.msgAdap.size()-1));
                    ChatPage.chatGroupAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void sendMessage(GroupChat p,Message m){
        getDatabaseReference().child(p.getUid()).child("messages").push().setValue(m);
        getDatabaseReference().child(p.getUid()).child("lastMessage").setValue(m);
        p.setLastMessage(m);
    }
}
