package c.bilgin.chatapplication.UsersGroupChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import c.bilgin.chatapplication.R;
import c.bilgin.chatapplication.UsersPersonalChat.Message;

public class GroupChatAdapter extends ArrayAdapter<GroupChat> {
    private List<GroupChat> groupChats;
    private Context mContext;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;
    private AdapterView.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public GroupChatAdapter(Context context, List<GroupChat> objects) {
        super(context, 0, objects);
        this.groupChats = objects;
        this.mContext = context;
        this.inflater = LayoutInflater.from(mContext);
    }


    @Override
    public GroupChat getItem(int position) {
        return groupChats.get(position);
    }


    private class ViewHolder {
        ImageButton imgButtonPersonProfile;
        TextView txtPersonFullName;
        TextView txtChatLastMessage,txtDate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)");

        if(convertView == null){

            convertView = inflater.inflate(R.layout.card_chat_list,null);

            viewHolder = new ViewHolder();
            viewHolder.imgButtonPersonProfile = (ImageButton) convertView.findViewById(R.id.imgButtonProfilePhoto);
            viewHolder.txtPersonFullName = (TextView)convertView.findViewById(R.id.txtFullName);
            viewHolder.txtChatLastMessage = (TextView)convertView.findViewById(R.id.txtLastMessage);
            viewHolder.txtDate = (TextView)convertView.findViewById(R.id.txtDate);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        GroupChat groupChat = groupChats.get(position);

        if(groupChat!= null){
            Picasso.with(mContext).load(groupChat.getGroupImage()).fit().into(viewHolder.imgButtonPersonProfile);
            viewHolder.txtPersonFullName.setText(groupChat.getGroupName());
            if(groupChat.getMessage()!=null){
                viewHolder.txtChatLastMessage.setText(groupChat.getLastMessage()==null?"":groupChat.getLastMessage().getMessage());
                viewHolder.txtDate.setText(groupChat.getLastMessage()==null?"":format.format(groupChat.getLastMessage().getMessage_date()));
            }else {
                viewHolder.txtChatLastMessage.setText("");
                viewHolder.txtDate.setText("");
            }


        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null) onItemClickListener.onItemClick(null,v,position,-1);
            }
        });
        return convertView;
    }


}
