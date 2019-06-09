package c.bilgin.chatapplication.QuestionPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;

public class QuestionChildFragmentFollow extends Fragment {

    private static QuestionChildFragmentFollow instance = new QuestionChildFragmentFollow();
    @SuppressLint("ValidFragment")
    private QuestionChildFragmentFollow(){ for(Question q:QuestionChildFragment.arrQuestion){
        if(q.getAsker().getUID().equals(HomePage.currentUser.getUID()))arrQuestion.add(q);
    }}
    public static QuestionChildFragmentFollow getInstance(){return instance;}


    /*
    *
    * main goal of this section : follow some questions you are interested and follow your questions
    * */


    private Context mContext;
    private List<Question> arrQuestion = new ArrayList<>();
    private QuestionAdapter adapter;
    private ListView lstQuestions;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.question_child_notifications_fragment,container,false);




        lstQuestions = (ListView)frameLayout.findViewById(R.id.lstQuestion);
        adapter = new QuestionAdapter(mContext,arrQuestion);
        lstQuestions.setAdapter(adapter);


        return frameLayout;
    }
}
