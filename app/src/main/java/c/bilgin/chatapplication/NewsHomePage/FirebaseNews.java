package c.bilgin.chatapplication.NewsHomePage;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import c.bilgin.chatapplication.Firebase;

public class FirebaseNews extends Firebase {
    public FirebaseNews() {
        super("News", News.class);
    }

    @Override
    public void getData(final List someArr) {
        getDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp:dataSnapshot.getChildren()) {
                    someArr.add(sp.getValue(getaClass()));
                }

                synchronized (someArr){
                    HomeFragment.adapter.notifyDataSetChanged();
                }

                /*
                * synchronized (someArr){
                    HomeFragment.getInstance().fillItem();
                }*/
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
}
