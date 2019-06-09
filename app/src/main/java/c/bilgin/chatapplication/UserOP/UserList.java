package c.bilgin.chatapplication.UserOP;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.ChatPage;
import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;
import c.bilgin.chatapplication.UsersGroupChat.FirebaseGroupChat;
import c.bilgin.chatapplication.UsersGroupChat.GroupChat;
import c.bilgin.chatapplication.UsersPersonalChat.FirebasePersonalChat;
import c.bilgin.chatapplication.UsersPersonalChat.PersonalChat;
import c.bilgin.chatapplication.UsersPersonalChat.PersonalChatFragment;

public class UserList extends Dialog {

    public static List<User> arrUsers = new ArrayList<>();
    private FirebaseUser f = new FirebaseUser();
    private Context mContext;
    private ListView lstUser;
    protected static UserAdapter userAdapter;
    private PersonalChat pcVer1;
    private PersonalChat pcVer2;

    public UserList(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        f.getData(arrUsers);
        setContentView(R.layout.user_start_chat_list);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
        lstUser = (ListView)findViewById(R.id.lstUserList);
        userAdapter = new UserAdapter(mContext,arrUsers);
        lstUser.setAdapter(userAdapter);


        lstUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FirebasePersonalChat p = new FirebasePersonalChat();
                pcVer1= new PersonalChat(HomePage.currentUser.getUID(),arrUsers.get(position).getUID(),HomePage.currentUser,arrUsers.get(position));
                pcVer2 = new PersonalChat(arrUsers.get(position).getUID(),HomePage.currentUser.getUID(),arrUsers.get(position),HomePage.currentUser);
                if(isChatExists()) {
                    p.addData(pcVer1, pcVer1.getUID());

                    p.getData(PersonalChatFragment.arrPersonalChats);
                }
                else{
                    //start activity. with that pc
                    //pcver2 main

                    Intent i = new Intent(mContext,ChatPage.class);
                    i.putExtra("Chat","Personal");
                    HomePage.fragmentTransaction = HomePage.fragmentManager.beginTransaction();
                    HomePage.fragmentTransaction.remove(PersonalChatFragment.getInstance()).commit();
                    mContext.startActivity(i);
                }
                dismiss();
            }
        });
    }


    private boolean isChatExists(){
        for(PersonalChat c :PersonalChatFragment.arrPersonalChats){
            if(c.getUID().equals(pcVer1.getUID())|| c.getUID().equals(pcVer2.getUID())) {ChatPage.currentPersonalChat = c;return false;}
        }
        return true;
    }



}
