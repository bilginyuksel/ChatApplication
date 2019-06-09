package c.bilgin.chatapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private Context mContext;

    private static ProfileFragment instance = new ProfileFragment();
    @SuppressLint("ValidFragment")
    private ProfileFragment(){

    }
    public static ProfileFragment getInstance(){
        return instance;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.profile_fragment,container,false);
        ImageView imgView = (ImageView)linearLayout.findViewById(R.id.imgProfilePhoto);
        TextView txtName = (TextView)linearLayout.findViewById(R.id.txtUserName);
        TextView txtGroups = (TextView)linearLayout.findViewById(R.id.txtGroups);
        TextView txtEmail = (TextView)linearLayout.findViewById(R.id.txtEmail);
        final EditText etMessage = (EditText)linearLayout.findViewById(R.id.etFCMMessage);
        Button btnSend = (Button)linearLayout.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked");
                /*
                * FirebaseMessaging fm = FirebaseMessaging.getInstance();
                fm.send(new RemoteMessage.Builder(HomePage.currentUser.getUID() + "@fcm.googleapis.com")
                        .setMessageId(Integer.toString(3))
                        .addData("my_message", etMessage.getText().toString())
                        .addData("my_action","SAY_HELLO")
                        .build());*/
            }
        });

       // String groups="";

        Picasso.with(mContext).load(HomePage.currentUser.getProfilePhoto()).fit().into(imgView);
        txtName.setText(HomePage.currentUser.getName() + " "+HomePage.currentUser.getSurname());
        txtEmail.setText(HomePage.currentUser.getEmail());
        txtGroups.setText("I deleted groups from users i have to add it...");




        return linearLayout;
    }
}
