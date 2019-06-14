package c.bilgin.chatapplication.UsersGroupChat;

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
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.ChatOperations.ChatPage;
import c.bilgin.chatapplication.Firebase;
import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;

public class GroupChatFragment extends Fragment {

    private Context mContext;
    private static GroupChatFragment instance = new GroupChatFragment();
    protected static GroupChatAdapter adapter;
    private List<GroupChat> groupChats = new ArrayList<>();
    private Firebase s = new FirebaseGroupChat();
    private ListView groupListView;

    @SuppressLint("ValidFragment")
    private GroupChatFragment(){s.getData(groupChats);}

    public static GroupChatFragment getInstance(){return instance;}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.group_chat_fragment,container,false);


        groupListView = (ListView)frameLayout.findViewById(R.id.lstGroupChat);
        adapter = new GroupChatAdapter(mContext,groupChats);
        groupListView.setAdapter(adapter);


        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(mContext, ChatPage.class);
                i.putExtra("Chat","Group");
                ChatPage.currentGroupChat =groupChats.get(position);
                HomePage.fragmentTransaction = HomePage.fragmentManager.beginTransaction();
                HomePage.fragmentTransaction.remove(GroupChatFragment.getInstance()).commit();
                startActivity(i);
            }
        });




        return frameLayout;
    }
}
