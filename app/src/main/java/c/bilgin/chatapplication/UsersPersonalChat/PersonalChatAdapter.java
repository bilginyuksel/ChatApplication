package c.bilgin.chatapplication.UsersPersonalChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import c.bilgin.chatapplication.R;

public class PersonalChatAdapter extends ArrayAdapter<PersonalChat> {

    private Context mContext;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;
    private List<PersonalChat> personalChats;
    private FirebaseAuth mAuth;
    private AdapterView.OnItemClickListener onItemClickListener;


    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PersonalChatAdapter(Context context, List<PersonalChat> personalChats) {
        super(context,0,personalChats);
        this.mContext = context;
        this.personalChats = personalChats;
        this.inflater = LayoutInflater.from(context);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public int getCount() {
        return personalChats.size();
    }

    @Override
    public PersonalChat getItem(int position) {
        return personalChats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return personalChats.get(position).hashCode();
    }


    private class ViewHolder {
        ImageButton imgButtonPersonProfile;
        TextView txtPersonFullName;
        TextView txtChatLastMessage,txtDate;
    }


    @Override
    public View getView(final int position,View convertView, ViewGroup parent) {


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

        PersonalChat personalChat = personalChats.get(position);

        if(personalChat!= null){
            Picasso.with(mContext).load(personalChat.getReceiver().getProfilePhoto()).fit().into(viewHolder.imgButtonPersonProfile);
            viewHolder.txtPersonFullName.setText(personalChat.getReceiver().getName() + " "+personalChat.getReceiver().getSurname());
            if(personalChat.getLastMessage()!=null) {
                viewHolder.txtChatLastMessage.setText(personalChat.getLastMessage().getMessage());
                viewHolder.txtDate.setText(format.format(personalChat.getLastMessage().getMessage_date()));
            }
            else{
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
