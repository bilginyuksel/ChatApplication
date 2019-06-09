package c.bilgin.chatapplication.QuestionPage;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import c.bilgin.chatapplication.Firebase;
import c.bilgin.chatapplication.HomePage;

public class FirebaseAnswer extends Firebase {
    public FirebaseAnswer() {
        super("Question/Answers", Answer.class);
    }

    @Override
    public void getData(final List someArr) {
        getDatabaseReference().child(HomePage.currentUser.getUID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp:dataSnapshot.getChildren()){
                    someArr.add(sp.getValue(getaClass()));
                }

                synchronized (someArr){
                    QuestionChildFragmentAnswers.adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void addData(Object o, String uid) {
        getDatabaseReference().child(HomePage.currentUser.getUID()).child(uid).setValue(o);
    }

    @Override
    public void updateData() {

    }
}
