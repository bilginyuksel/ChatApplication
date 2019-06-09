package c.bilgin.chatapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;


import c.bilgin.chatapplication.NewsHomePage.HomeFragment;
import c.bilgin.chatapplication.UserOP.User;
import c.bilgin.chatapplication.UsersGroupChat.GroupChatFragment;
import c.bilgin.chatapplication.UsersPersonalChat.PersonalChatFragment;
import c.bilgin.chatapplication.QuestionPage.QuestionFragment;

public class HomePage extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static FragmentTransaction fragmentTransaction;
    public static User currentUser;
    private BottomNavigationView navigation;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layout,HomeFragment.getInstance());
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_question:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layout,QuestionFragment.getInstance());
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_chat:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layout,PersonalChatFragment.getInstance());
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_groupChat:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layout,GroupChatFragment.getInstance());
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_profile:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layout,ProfileFragment.getInstance());
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        fragmentManager = this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.add(R.id.layout,HomeFragment.getInstance());
        fragmentTransaction.commit();



    }



    @Override
    public void onBackPressed() {
        //do cool stuff

        //you really want to left application bla bla

        //ask do you want to logout bla bla.
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to logout ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginPage.sharedPreferences.edit().clear().apply();
                startActivity(new Intent(HomePage.this,LoginPage.class));
            }
        });
        builder.setCancelable(false);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();




    }
}
