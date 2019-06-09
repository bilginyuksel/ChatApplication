package c.bilgin.chatapplication.UserOP;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

import c.bilgin.chatapplication.Firebase;
import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.MyFirebaseMessagingService;
import c.bilgin.chatapplication.UsersPersonalChat.PersonalChat;
import c.bilgin.chatapplication.UsersPersonalChat.PersonalChatFragment;

public class FirebaseUser extends Firebase {

    private FirebaseAuth mAuth;
    public FirebaseUser() {
        super("User", User.class);mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void getData(final List someArr) {
        someArr.clear();
        getDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp:dataSnapshot.getChildren()) {
                    someArr.add(sp.getValue(getaClass()));
                }
                
                synchronized (someArr){
                    UserList.userAdapter.notifyDataSetChanged();
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

    public void getCurrentUser(){

       // while(mAuth.getCurrentUser()==null) continue;
        getDatabaseReference().orderByChild("uid").equalTo(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp:dataSnapshot.getChildren())
                    HomePage.currentUser = sp.getValue(User.class);

                getDatabaseReference().child(HomePage.currentUser.getUID()).child("message_token").setValue(FirebaseInstanceId.getInstance().getToken());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private User u;



}
