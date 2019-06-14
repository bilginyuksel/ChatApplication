package c.bilgin.chatapplication.ChatOperations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;
import c.bilgin.chatapplication.UsersPersonalChat.Message;

public class ChatPersonalAdapter extends ArrayAdapter<Message>
{
    private Context mContext;
    private List<Message> messageList;
    private ViewHolder viewholder;
    private LayoutInflater inflater;


    private class ViewHolder{
        TextView txtMessage,txtMessageTime;
        LinearLayout linearLayout;

    }


    public ChatPersonalAdapter(Context context,List<Message> messageList) {
        super(context, 0);
        this.mContext = context;
        this.messageList = messageList;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public Message getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        DateFormat format = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)");
        Message m = messageList.get(position);
        if(m!= null){

            if(m.getSender().getUID().equals(HomePage.currentUser.getUID())){
                convertView = inflater.inflate(R.layout.card_message,null);

                viewholder = new ViewHolder();

                viewholder.txtMessage = (TextView)convertView.findViewById(R.id.txtMessage);
                viewholder.txtMessageTime = (TextView)convertView.findViewById(R.id.txtMessage_time);
                viewholder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linLay);

                viewholder.txtMessageTime.setText(format.format(m.getMessage_date()));
                viewholder.txtMessage.setText(m.getMessage());
                convertView.setTag(viewholder);
            }else{
                convertView = inflater.inflate(R.layout.card_their_message,null);

                viewholder = new ViewHolder();

                viewholder.txtMessage = (TextView)convertView.findViewById(R.id.txtMessage);
                viewholder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linLay);
                viewholder.txtMessageTime = (TextView)convertView.findViewById(R.id.txtMessage_time);


                viewholder.txtMessage.setText(m.getMessage());
                viewholder.txtMessageTime.setText(format.format(m.getMessage_date()));
                convertView.setTag(viewholder);

            }
        }


        return convertView;
    }
}
