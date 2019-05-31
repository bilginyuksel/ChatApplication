package c.bilgin.chatapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


import c.bilgin.chatapplication.News;
import c.bilgin.chatapplication.R;

public class HomeFragment extends Fragment {

    private Context mContext;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("News");
    private List<News> arrNews = new ArrayList<>();
    private FrameLayout frameLayout;
    private ImageView imageView;
    private TabLayout tabLayout;
    private TextView txtTitle,txtDescription;
    private String picked ="AI";
    private News n;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNewsData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //create framelayout and fill it here
        frameLayout = (FrameLayout)inflater.inflate(R.layout.home_fragment,container,false);
        imageView= (ImageView) frameLayout.findViewById(R.id.imgNews);
        txtDescription = (TextView)frameLayout.findViewById(R.id.description);
        txtTitle = (TextView)frameLayout.findViewById(R.id.title);

        //create tabs here
        tabLayout = frameLayout.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("AI"));
        tabLayout.addTab(tabLayout.newTab().setText("CS"));
        tabLayout.addTab(tabLayout.newTab().setText("VR"));
        tabLayout.addTab(tabLayout.newTab().setText("SD"));




        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getText().toString()){
                    case "CS":
                        picked = "CS";
                        break;
                    case "SD":
                        picked = "SD";
                        break;
                    case "AI":
                        picked = "AI";
                        break;
                    case "VR":
                        picked ="VR";
                        break;

                }
                n = findNewsObject(picked);
                Picasso.with(mContext).load(n.getImage()).fit().into(imageView);
                txtDescription.setText(n.getDescription());
                txtTitle.setText(n.getTitle());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //direct link to website.. when image clicked
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = n.getLink();
                if (!n.getLink().startsWith("http://") && !n.getLink().startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });



        //return framelayout for view
        return frameLayout;
    }



    private void getNewsData(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp : dataSnapshot.getChildren())
                    arrNews.add(sp.getValue(News.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private News findNewsObject(String pick){
        for(News n : arrNews)
            if(n.getGroup().equals(pick))
                return n;

        return null;
    }
}
