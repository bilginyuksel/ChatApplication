package c.bilgin.chatapplication.QuestionPage;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import c.bilgin.chatapplication.Firebase;
import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.ProfileFragment;

public class FirebaseAnswer extends Firebase {
    public FirebaseAnswer() {
        super("Question/Answers", Answer.class);
    }

    @Override
    public void getData(final List someArr) {

        getDatabaseReference().child(HomePage.currentUser.getUID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                someArr.clear();
                for(DataSnapshot sp:dataSnapshot.getChildren()){
                    someArr.add(sp.getValue(getaClass()));
                }

                synchronized (someArr){
                    QuestionChildFragmentAnswers.adapter.notifyDataSetChanged();
                    ProfileFragment.answer = someArr.size();
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

    public void updateRate(Answer a){
        getDatabaseReference().child(a.getAnswerer().getUID()).child(a.getUid()).child("rate").setValue(a.getRate());
    }


}
