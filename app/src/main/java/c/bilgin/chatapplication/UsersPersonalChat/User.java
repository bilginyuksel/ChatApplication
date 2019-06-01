package c.bilgin.chatapplication.UsersPersonalChat;

import java.util.UUID;

public class User {
    //User informations
    private String uuid,name,surname,email;
    private String groups[];

    public User(String name,String surname,String email,String groups[]){
        uuid = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.groups = groups;
    }

}
