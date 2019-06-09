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

import c.bilgin.chatapplication.R;

public class QuestionChildFragmentAnswers extends Fragment {

    private static QuestionChildFragmentAnswers instance = new QuestionChildFragmentAnswers();
    @SuppressLint("ValidFragment")
    private QuestionChildFragmentAnswers(){new FirebaseAnswer().getData(arrAnswer);}
    public static QuestionChildFragmentAnswers getInstance(){return instance;}

    private Context mContext;
    private List<Answer> arrAnswer = new ArrayList<>();
    protected static AnswerAdapter adapter;
    private ListView lstAnswers;


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
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.question_child_answer_fragment,container,false);

        lstAnswers = (ListView)frameLayout.findViewById(R.id.lstAnswer);
        adapter = new AnswerAdapter(mContext,arrAnswer);
        lstAnswers.setAdapter(adapter);




        return frameLayout;
    }
}
