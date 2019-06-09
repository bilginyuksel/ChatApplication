package c.bilgin.chatapplication.UserOP;

import com.google.firebase.iid.FirebaseInstanceId;

public class User {
    //User informations
    private String uid,name,surname,email,profilePhoto,message_token;



    public User(String uid, String name, String surname, String email, String profilePhoto,String message_token){
        this.uid = uid;
        this.name = name;
        this.message_token = message_token;
        this.surname = surname;
        this.email = email;
        this.profilePhoto = profilePhoto;
    }public User(){}

    public String getUID() {
        return uid;
    }

    public String getMessage_token() {
        return message_token;
    }

    public void setMessage_token(String message_token) {
        this.message_token = message_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUID(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public String getProfilePhoto() {
        return profilePhoto;
    }
}
