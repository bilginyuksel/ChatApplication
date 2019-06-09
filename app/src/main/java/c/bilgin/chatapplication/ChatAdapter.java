package c.bilgin.chatapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import c.bilgin.chatapplication.UsersPersonalChat.Message;

public class ChatAdapter extends ArrayAdapter<Message> {

    private Context mContext;
    private List<Message> messageList;
    private ViewHolder viewholder;
    private LayoutInflater inflater;


    private class ViewHolder{
        TextView txtSender;
        TextView txtMessage;
        LinearLayout linearLayout;

    }


    public ChatAdapter(Context context,List<Message> messageList) {
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

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.card_message,null);

            viewholder = new ViewHolder();

            viewholder.txtSender = (TextView)convertView.findViewById(R.id.txtSender);
            viewholder.txtMessage = (TextView)convertView.findViewById(R.id.txtMessage);
            viewholder.linearLayout = (LinearLayout)convertView.findViewById(R.id.linLay);

            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder)convertView.getTag();
        }


        Message m = messageList.get(position);
        if(m!= null){
            viewholder.txtSender.setText( m.getSender().getName() + " >>");

            viewholder.txtMessage.setText(m.getMessage());
        }

        return convertView;
    }
}
