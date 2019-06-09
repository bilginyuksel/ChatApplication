package c.bilgin.chatapplication.UsersPersonalChat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;


import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.ChatPage;
import c.bilgin.chatapplication.Firebase;
import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;
import c.bilgin.chatapplication.UserOP.User;
import c.bilgin.chatapplication.UserOP.UserList;


public class PersonalChatFragment extends Fragment {

    private Context mContext;
    public static List<PersonalChat> arrPersonalChats = new ArrayList<>();
    private Firebase fPChat = new FirebasePersonalChat();
    private FirebaseAuth mAuth;
    protected static PersonalChatAdapter personalChatAdapter;
    public static User receiver;
    private static PersonalChatFragment instance = new PersonalChatFragment();

    @SuppressLint("ValidFragment")
    private PersonalChatFragment(){fPChat.getData(arrPersonalChats);
         }
    public static PersonalChatFragment getInstance(){
        return instance;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fill array here. or use adapter.
        //filter data here or in Firebase class



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.personal_chat_fragment,container,false);
        //if you click some list item send it to chat_Screen use it like child fragment
        //or think some other way if it exists


        ImageButton personalChatPlusButton = (ImageButton)frameLayout.findViewById(R.id.personalChatPlusButton);
        EditText personalChatSearchText =(EditText)frameLayout.findViewById(R.id.personalChatSearchText);
        final ListView personalChatListView = (ListView)frameLayout.findViewById(R.id.personalChatList);
        personalChatAdapter = new PersonalChatAdapter(mContext,arrPersonalChats);
        personalChatListView.setAdapter(personalChatAdapter);





        //plus button click
        personalChatPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserList s = new UserList(mContext);
                s.show();



            }
        });


        personalChatAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //find sender and receiver here

                Intent i = new Intent(mContext,ChatPage.class);
                i.putExtra("Chat","Personal");
                ChatPage.currentPersonalChat =arrPersonalChats.get(position);
                startActivity(i);
                HomePage.fragmentTransaction = HomePage.fragmentManager.beginTransaction();
                HomePage.fragmentTransaction.remove(PersonalChatFragment.getInstance()).commit();
            }
        });


        return frameLayout;
    }
}
