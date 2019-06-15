package c.bilgin.chatapplication.QuestionPage;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

import c.bilgin.chatapplication.Firebase;

public class FirebaseQuestion extends Firebase {
    public FirebaseQuestion() {
        super("Question/Questions", Question.class);
    }

    private Question q;
    @Override
    public void getData(final List someArr) {
        getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                someArr.clear();
                for(DataSnapshot sp: dataSnapshot.getChildren()){
                    q =(Question)sp.getValue(getaClass());
                    DataSnapshot sp1 = sp.child("answers");
                    HashMap<String,Answer> ans = new HashMap<>();
                    for(DataSnapshot s : sp1.getChildren()){
                        ans.put(s.getKey(),s.getValue(Answer.class));
                    }
                    q.setAnswers(ans);
                    someArr.add(q);
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
