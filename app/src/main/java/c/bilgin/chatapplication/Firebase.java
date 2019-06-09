package c.bilgin.chatapplication;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public abstract class Firebase {
    private DatabaseReference databaseReference;
    private Class aClass;

    public Firebase(String reference,Class aClass){
        this.databaseReference =FirebaseDatabase.getInstance().getReference(reference);
        this.aClass = aClass;
    }

    public abstract void getData(List someArr);
    public abstract void addData(Object o,String uid);
    public abstract void updateData();



    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public void setDatabaseReference(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public Class getaClass() {
        return aClass;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
