package c.bilgin.chatapplication.QuestionPage;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import c.bilgin.chatapplication.Firebase;

public class FirebaseQuestion extends Firebase {
    public FirebaseQuestion() {
        super("Question/Questions", Question.class);
    }

    @Override
    public void getData(final List someArr) {
        getDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp:dataSnapshot.getChildren()){
                    someArr.add(sp.getValue(getaClass()));
                }

                synchronized (someArr){
                    QuestionChildFragment.adapter.notifyDataSetChanged();
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

    public void updateRate(Question q){
        getDatabaseReference().child(q.getUid()).child("rate").setValue(q.getRate());
    }

    public void addAnswer(Question q){
        getDatabaseReference().child(q.getUid()).child("answers").setValue(q.getAnswers());
    }
}
