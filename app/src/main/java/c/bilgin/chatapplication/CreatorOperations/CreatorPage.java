package c.bilgin.chatapplication.CreatorOperations;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;

public class CreatorPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_page);
        Toast.makeText(this, "Welcome to GOD MODE \n"+HomePage.currentUser.getName(), Toast.LENGTH_LONG).show();

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerCreatorAdapter(getSupportFragmentManager()));

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomePage.class));
    }
}
