package c.bilgin.chatapplication.NewsHomePage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.R;

public class VRFragment extends Fragment {


    private Context mContext;
    public static List<News> arrVRNews = new ArrayList<>();
    private FrameLayout frameLayout;
    private ListView lstNews;
    protected static NewsAdapter adapter;
    private News n;
    private static VRFragment instance = new VRFragment();
    @SuppressLint("ValidFragment")
    private VRFragment(){
        new FirebaseNews().getVRData(arrVRNews);
    }
    public static VRFragment getInstance(){return instance;}

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
        frameLayout = (FrameLayout)inflater.inflate(R.layout.vr_news_fragment,container,false);

        lstNews= (ListView)frameLayout.findViewById(R.id.lstNews);
        adapter = new NewsAdapter(mContext,arrVRNews);
        lstNews.setAdapter(adapter);



        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                n = arrVRNews.get(position);
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
}
