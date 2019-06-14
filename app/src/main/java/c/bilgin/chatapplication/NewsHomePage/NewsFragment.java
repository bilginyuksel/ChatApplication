package c.bilgin.chatapplication.NewsHomePage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import c.bilgin.chatapplication.R;

public class NewsFragment extends Fragment {


    private static NewsFragment instance = new NewsFragment();
    @SuppressLint("ValidFragment")
    private NewsFragment(){}
    public static NewsFragment getInstance(){return instance;}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.all_news_fragment,null);

        ViewPager viewPager = (ViewPager)frameLayout.findViewById(R.id.news_view_pager);
        viewPager.setAdapter(new ViewPagerNewsAdapter(getChildFragmentManager()));

        return frameLayout;

    }
}
