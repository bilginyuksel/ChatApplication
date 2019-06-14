package c.bilgin.chatapplication;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import c.bilgin.chatapplication.CreatorOperations.CreatorPage;

public class ProfileFragment extends Fragment {

    private Context mContext;

    public static int answer=0;
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
        TextView txtEmail = (TextView)linearLayout.findViewById(R.id.txtEmail);
        TextView txtAnsweredQuestion = (TextView)linearLayout.findViewById(R.id.txtAnsweredQuestion);

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HomePage.currentUser.getAuthority().equals("Creator")){
                    //Start activity
                    //For adding news, adding users, controlling user activities maybe
                    //deleting something from the app...
                    startActivity(new Intent(mContext, CreatorPage.class));
                    HomePage.fragmentTransaction = HomePage.fragmentManager.beginTransaction();
                    HomePage.fragmentTransaction.remove(ProfileFragment.getInstance()).commit();
                }else{
                    Toast.makeText(mContext, "Sorry members can access that area!", Toast.LENGTH_SHORT).show();
                }
            }
        });


       // String groups="";

        Picasso.with(mContext).load(HomePage.currentUser.getProfilePhoto()).fit().into(imgView);
        txtName.setText(HomePage.currentUser.getName() + " "+HomePage.currentUser.getSurname());
        txtEmail.setText(HomePage.currentUser.getEmail());
        txtAnsweredQuestion.setText(answer==0?"No answers yet.":answer+" Answers.");




        return linearLayout;
    }
}
