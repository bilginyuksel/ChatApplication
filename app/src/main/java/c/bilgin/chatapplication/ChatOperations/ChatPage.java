package c.bilgin.chatapplication.ChatOperations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;
import c.bilgin.chatapplication.UsersGroupChat.FirebaseGroupChat;
import c.bilgin.chatapplication.UsersGroupChat.GroupChat;
import c.bilgin.chatapplication.UsersPersonalChat.FirebasePersonalChat;
import c.bilgin.chatapplication.UsersPersonalChat.Message;
import c.bilgin.chatapplication.UsersPersonalChat.PersonalChat;

public class ChatPage extends AppCompatActivity {


    public static PersonalChat currentPersonalChat;
    public static GroupChat currentGroupChat;
    private ListView lstViewMessages;
    private TextView txtViewGuest;
    private EditText etMessage;
    private ImageView chatImage;
    private ImageButton btnSendMessage;
    public static ChatGroupAdapter chatGroupAdapter;
    public static ChatPersonalAdapter chatPersonalAdapter;
    public static List<Message> messages;
    public static List<Message> msgAdap;

    private void __init__(){
        lstViewMessages = (ListView)findViewById(R.id.lstViewMessages);
        txtViewGuest = (TextView)findViewById(R.id.pcWithWho);
        etMessage = (EditText)findViewById(R.id.etMessage);
        btnSendMessage = (ImageButton) findViewById(R.id.btnSendMessage);
        chatImage = (ImageView)findViewById(R.id.chatImage);




        //configuration stuff
        if(isPersonal()){
            //if personal message
            personalMessageConfiguration();
        }
        else{
            //if group message
           groupChatConfiguration();
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        __init__();

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String message = etMessage.getText().toString();
                    etMessage.setText("");
                    if(!message.isEmpty() || message.trim().length() != 0){ //control message with no just spaces
                        sendMessage(message);
                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void sendMessage(String message){
        Message m = new Message(message, HomePage.currentUser);
        if(isPersonal()){
            new FirebasePersonalChat().sendMessage(currentPersonalChat,m);
            lstViewMessages.setSelection(chatPersonalAdapter.getCount());
        }else{
            new FirebaseGroupChat().sendMessage(currentGroupChat,m);
            lstViewMessages.setSelection(chatGroupAdapter.getCount());
        }

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,HomePage.class));
    }


    private void personalMessageConfiguration(){

        Picasso.with(this).load(currentPersonalChat.getReceiver().getProfilePhoto()).fit().into(chatImage);
        txtViewGuest.setText(currentPersonalChat.getReceiver().getName()+" "+currentPersonalChat.getReceiver().getSurname());


        messages = new ArrayList<>(currentPersonalChat.getMessage().values());

        new FirebasePersonalChat().listenMessages(currentPersonalChat);



        chatPersonalAdapter = new ChatPersonalAdapter(this,messages);
        lstViewMessages.setAdapter(chatPersonalAdapter);
        lstViewMessages.setSelection(chatPersonalAdapter.getCount());

    }


    private void groupChatConfiguration(){
        Picasso.with(this).load(currentGroupChat.getGroupImage()).fit().into(chatImage);
        txtViewGuest.setText(currentGroupChat.getGroupName());



        //while map listening the messages when new message came up list should be updated.
        //but in every child add process if list sorts maybe its not a good idea but brute force solution is this
        msgAdap = new ArrayList<>(currentGroupChat.getMessage().values());



        new FirebaseGroupChat().listenMessages(currentGroupChat); //while map listening messages list should update



        chatGroupAdapter = new ChatGroupAdapter(this,msgAdap);
        lstViewMessages.setAdapter(chatGroupAdapter);
        lstViewMessages.setSelection(chatGroupAdapter.getCount());
    }



    private boolean isPersonal(){
        return getIntent().getStringExtra("Chat").equals("Personal")?true:false;
    }



}
