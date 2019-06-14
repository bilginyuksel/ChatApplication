package c.bilgin.chatapplication.UserOP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import c.bilgin.chatapplication.R;


public class UserAdapter extends ArrayAdapter<User> {

    private List<User> users;
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

    public UserAdapter(Context context, List<User> users) {
        super(context, 0,users);
        this.mContext = context;
        this.users = users;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }


    private class ViewHolder{
        ImageView imgUserProfile;
        TextView txtUserFullName;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.card_user_list,null);

            viewHolder = new ViewHolder();
            viewHolder.imgUserProfile = (ImageView)convertView.findViewById(R.id.imgViewUserProfile);
            viewHolder.txtUserFullName = (TextView)convertView.findViewById(R.id.txtUserFullName);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        User user = users.get(position);

        if(user!=null){
            Picasso.with(mContext).load(user.getProfilePhoto()).fit().into(viewHolder.imgUserProfile);
            viewHolder.txtUserFullName.setText(user.getName()+" "+user.getSurname());

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
