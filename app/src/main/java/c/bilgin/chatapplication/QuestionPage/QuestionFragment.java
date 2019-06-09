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

        fragmentManager = this.getChildFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.add(R.id.layout,QuestionChildFragment.getInstance());
        fragmentTransaction.commit();
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

        TabLayout tabLayout = frameLayout.findViewById(R.id.tabs);
        //Create tabs here
        tabLayout.addTab(tabLayout.newTab().setText("Questions"));
        tabLayout.addTab(tabLayout.newTab().setText("Answers"));
        tabLayout.addTab(tabLayout.newTab().setText("Follow"));

        /*
        * child fragments select listener // if you need fill unselected and reselected*/
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()){
                    case QUESTION:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.layout,QuestionChildFragment.getInstance());
                        fragmentTransaction.commit();
                        break;
                    case ANSWER:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.layout,QuestionChildFragmentAnswers.getInstance());
                        fragmentTransaction.commit();
                        break;
                    case NOTIFICATION:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.layout, QuestionChildFragmentFollow.getInstance());
                        fragmentTransaction.commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });//opens fragment according to tab





        return frameLayout;
    }
}
