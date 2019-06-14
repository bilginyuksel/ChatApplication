package c.bilgin.chatapplication.NewsHomePage;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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

    public void getAIData(final List someArr){
        getDatabaseReference().orderByChild("group").equalTo("Artificial Intelligence").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp :dataSnapshot.getChildren()){
                    someArr.add(sp.getValue(getaClass()));
                }

                synchronized (someArr){
                  AIFragment.adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getCSData(final List someArr){
        getDatabaseReference().orderByChild("group").equalTo("Cyber Security").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp :dataSnapshot.getChildren()){
                    someArr.add(sp.getValue(getaClass()));
                }

                synchronized (someArr){
                    if(CSFragment.CSadapter!=null)CSFragment.CSadapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void getVRData(final List someArr){
        getDatabaseReference().orderByChild("group").equalTo("Virtual Reality").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp :dataSnapshot.getChildren()){
                    someArr.add(sp.getValue(getaClass()));
                }

                synchronized (someArr){
                    if(VRFragment.adapter!=null)
                        VRFragment.adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void getSDData(final List someArr){
        getDatabaseReference().orderByChild("group").equalTo("Software Development").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp :dataSnapshot.getChildren()){
                    someArr.add(sp.getValue(getaClass()));
                }

                synchronized (someArr){
                    if(SDFragment.adapter!=null)SDFragment.adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
