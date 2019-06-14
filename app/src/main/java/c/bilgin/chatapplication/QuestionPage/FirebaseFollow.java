package c.bilgin.chatapplication.QuestionPage;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

import c.bilgin.chatapplication.Firebase;
import c.bilgin.chatapplication.HomePage;

public class FirebaseFollow extends Firebase {
    public FirebaseFollow() {
        super("Question/Follow", Question.class);
    }

    private Question q;

    @Override
    public void getData(final List someArr) {
        getDatabaseReference().child(HomePage.currentUser.getUID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(someArr!=null)someArr.clear();

                for(DataSnapshot sp: dataSnapshot.getChildren()){
                    q =(Question)sp.getValue(getaClass());
                    DataSnapshot sp1 = sp.child("ans");
                    HashMap<String,Answer> ans = new HashMap<>();
                    for(DataSnapshot s : sp1.getChildren()){
                        ans.put(s.getKey(),s.getValue(Answer.class));
                    }
                    q.setAns(ans);
                    someArr.add(q);
                }

                synchronized (someArr){
                    if(QuestionChildFragmentFollow.adapter!=null)
                    QuestionChildFragmentFollow.adapter.notifyDataSetChanged();
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
    public void addAnswer(Question q){
        getDatabaseReference().child(HomePage.currentUser.getUID()).child(q.getUid()).child("ans").setValue(q.getAns());
    }
}
