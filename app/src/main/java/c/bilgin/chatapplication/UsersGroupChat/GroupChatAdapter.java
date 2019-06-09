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

import java.util.List;

import c.bilgin.chatapplication.R;

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
        TextView txtChatLastMessage;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = inflater.inflate(R.layout.card_chat_list,null);

            viewHolder = new ViewHolder();
            viewHolder.imgButtonPersonProfile = (ImageButton) convertView.findViewById(R.id.imgButtonProfilePhoto);
            viewHolder.txtPersonFullName = (TextView)convertView.findViewById(R.id.txtFullName);
            viewHolder.txtChatLastMessage = (TextView)convertView.findViewById(R.id.txtLastMessage);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        GroupChat groupChat = groupChats.get(position);

        if(groupChat!= null){
            Picasso.with(mContext).load(groupChat.getGroupImage()).fit().into(viewHolder.imgButtonPersonProfile);
            viewHolder.txtPersonFullName.setText(groupChat.getGroupName());
            if(groupChat.getMessages()!=null)
                viewHolder.txtChatLastMessage.setText(groupChat.getMessages().size()!=0?groupChat.getMessages().get(groupChat.getMessages().size()-1).getMessage():"");
            else viewHolder.txtChatLastMessage.setText("");
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
