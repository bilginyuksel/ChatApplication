package c.bilgin.chatapplication;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class Firebase {
    private DatabaseReference databaseReference;
    private Class aClass;

    public Firebase(String reference,Class aClass){
        this.databaseReference =FirebaseDatabase.getInstance().getReference(reference);
        this.aClass = aClass;
    }

    public void getData(final List someArr){
        this.databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp:dataSnapshot.getChildren()) {
                    someArr.add(sp.getValue(aClass));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
