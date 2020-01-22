package c.bilgin.chatapplication.QuestionPage;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.R;

public class QuestionChildFragment extends Fragment {


    private ListView lstQuestion;
    protected static QuestionAdapter adapter;
    protected static List<Question> arrQuestion = new ArrayList<>();
    private Button addQ;
    private String name="Questions";
    private Context mContext;
    private static QuestionChildFragment instance = new QuestionChildFragment();
    @SuppressLint("ValidFragment")
    private QuestionChildFragment(){ new FirebaseQuestion().getData(arrQuestion);}
    public static QuestionChildFragment getInstance(){return instance;}




    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context); this.mContext = context;
    }

    public String getName() {
        return name;
    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.question_child_fragment,container,false);

        addQ = (Button)frameLayout.findViewById(R.id.addQ);
        lstQuestion = (ListView)frameLayout.findViewById(R.id.lstQuestions);
        adapter = new QuestionAdapter(mContext,arrQuestion);
        lstQuestion.setAdapter(adapter);

        addQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog d = new AskQuestionDialog(mContext);
                d.show();

            }
        });


        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              if(arrQuestion.get(position).getAnswers()!=null){
                  DialogShowAnswers d =new DialogShowAnswers(mContext,arrQuestion.get(position));
                  d.show();
              }else{
                  Toast.makeText(mContext, "Sorry no answers for that question.", Toast.LENGTH_SHORT).show();
              }
            }
        });



        return frameLayout;
    }
}
