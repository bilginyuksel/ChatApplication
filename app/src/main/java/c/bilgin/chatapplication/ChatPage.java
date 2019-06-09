package c.bilgin.chatapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.UsersGroupChat.FirebaseGroupChat;
import c.bilgin.chatapplication.UsersGroupChat.GroupChat;
import c.bilgin.chatapplication.UsersPersonalChat.FirebasePersonalChat;
import c.bilgin.chatapplication.UsersPersonalChat.Message;
import c.bilgin.chatapplication.UsersPersonalChat.PersonalChat;
import c.bilgin.chatapplication.UsersPersonalChat.PersonalChatFragment;

public class ChatPage extends AppCompatActivity {

    public static PersonalChat currentPersonalChat;
    public static GroupChat currentGroupChat;
    private ListView lstViewMessages;
    private TextView txtViewGuest;
    private EditText etMessage;
    private ImageView chatImage;
    private ImageButton btnSendMessage;
    public static ChatAdapter chatAdapter;
    public static List<Message> messages;
    //private List<Message> messageList;

    private void __init__(){
        lstViewMessages = (ListView)findViewById(R.id.lstViewMessages);
        txtViewGuest = (TextView)findViewById(R.id.pcWithWho);
        etMessage = (EditText)findViewById(R.id.etMessage);
        btnSendMessage = (ImageButton) findViewById(R.id.btnSendMessage);
        chatImage = (ImageView)findViewById(R.id.chatImage);

        //messageList = currentPersonalChat.getMessages();
        //Adapter and settext
        if(getIntent().getStringExtra("Chat").equals("Personal")){
            if(currentPersonalChat.getMessages() == null){
                messages = new ArrayList<>();
            }else{
                messages = currentPersonalChat.getMessages();
                messages.remove(messages.size()-1);
            }

            Picasso.with(this).load(currentPersonalChat.getReceiver().getProfilePhoto()).fit().into(chatImage);
            currentPersonalChat.setMessages((ArrayList<Message>)messages);
            new FirebasePersonalChat().listenMessages(currentPersonalChat);
            txtViewGuest.setText(currentPersonalChat.getReceiver().getName()+" "+currentPersonalChat.getReceiver().getSurname());

        }else{
            if(currentGroupChat.getMessages() == null){
                messages = new ArrayList<>();
            }else{
                messages = currentGroupChat.getMessages();
                if(messages.size()!=0)
                    messages.remove(messages.size()-1);
            }

            Picasso.with(this).load(currentGroupChat.getGroupImage()).fit().into(chatImage);
            currentGroupChat.setMessages(messages);
            new FirebaseGroupChat().listenMessages(currentGroupChat);
            txtViewGuest.setText(currentGroupChat.getGroupName());
        }



        chatAdapter = new ChatAdapter(this,messages);
        lstViewMessages.setAdapter(chatAdapter);

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
                    Thread.sleep(500);
                    if(!message.isEmpty() || message.trim().length() != 0){
                        if(getIntent().getStringExtra("Chat").equals("Personal")){
                            new FirebasePersonalChat().sendMessage(currentPersonalChat,new Message(message,HomePage.currentUser));
                        }else{
                            new FirebaseGroupChat().sendMessage(currentGroupChat,new Message(message,HomePage.currentUser));
                        }

                        chatAdapter.notifyDataSetChanged();
                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,HomePage.class));
    }
}
