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
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;

public class QuestionChildFragmentFollow extends Fragment {

    protected static List<Question> arrQuestion = new ArrayList<>();
    private static QuestionChildFragmentFollow instance = new QuestionChildFragmentFollow();
    @SuppressLint("ValidFragment")
    private QuestionChildFragmentFollow(){
        new FirebaseFollow().getData(arrQuestion);
    }
    public static QuestionChildFragmentFollow getInstance(){return instance;}

    private Context mContext;

    protected static QuestionAdapter adapter;
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


        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(arrQuestion.get(position).getAns()!=null){
                    DialogShowAnswers d = new DialogShowAnswers(mContext,arrQuestion.get(position));
                    d.show();
                }else{
                    Toast.makeText(mContext, "Sorry no answers for that question.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return frameLayout;
    }
}
