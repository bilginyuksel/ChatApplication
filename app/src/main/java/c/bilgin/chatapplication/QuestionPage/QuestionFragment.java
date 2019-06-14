package c.bilgin.chatapplication.QuestionPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import c.bilgin.chatapplication.R;

public class QuestionFragment extends Fragment {

    private Context mContext;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private static final String QUESTION = "Questions";
    private static final String ANSWER = "Answers";
    private static final String NOTIFICATION = "Notifications";
    private static QuestionFragment instance = new QuestionFragment();

    @SuppressLint("ValidFragment")
    private QuestionFragment(){}

    public static QuestionFragment getInstance(){return instance;}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Questions");


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.question_fragment,container,false);
        ViewPager viewPager = (ViewPager)frameLayout.findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

        TabLayout tabLayout = frameLayout.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        return frameLayout;
    }
}
