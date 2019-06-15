package c.bilgin.chatapplication.QuestionPage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;

public class DialogShowAnswers extends Dialog {


    private Context mContext;
    private Question currentQuestion;
    private TextView txtInterest,txtQuestion,txtHowManyAnswers;
    private ListView lstAnswer;
    private ImageButton btnAnswer,btnFollow,btnDismiss;
    private CustomAnswerAdapter adapter;

    public DialogShowAnswers(Context context,Question q) {
        super(context);
        this.mContext = context;
        this.currentQuestion = q;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_show_answers);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);

        txtInterest = (TextView)findViewById(R.id.txtDialogInterest);
        txtQuestion = (TextView)findViewById(R.id.txtDialogQuestion);
        txtHowManyAnswers = (TextView)findViewById(R.id.txtDialogAnsCount);
        lstAnswer = (ListView)findViewById(R.id.dialoglstView);
        btnAnswer = (ImageButton)findViewById(R.id.dialogBtnAnswer);
        btnFollow = (ImageButton)findViewById(R.id.dialogBtnFollow);
        btnDismiss = (ImageButton)findViewById(R.id.btnDismiss);


        String interest="";
        for(String s:currentQuestion.getInterest())
            interest+="#"+s+" ";
        txtInterest.setText(interest);
        txtQuestion.setText(currentQuestion.getQuestion());
        txtHowManyAnswers.setText(currentQuestion.getAnswers()!=null?currentQuestion.getAnswers().size() +" Answers":"No answers yet.");
        List<Answer> ans = new ArrayList<>(currentQuestion.getAnswers().values());
        adapter = new CustomAnswerAdapter(mContext,ans,currentQuestion);
        lstAnswer.setAdapter(adapter);




        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAnswered(currentQuestion)){
                    AnswerDialog d = new AnswerDialog(mContext,currentQuestion);
                    d.show();
                }else{
                    Toast.makeText(mContext, "You've already answered to that question!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseFollow().addData(currentQuestion,currentQuestion.getUid());
                Toast.makeText(mContext, "Follow proceess for question.", Toast.LENGTH_LONG).show();
            }
        });
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private boolean isAnswered(Question q){
        for(Answer a:q.getAnswers().values()){
            if(a.getAnswerer().getUID().equals(HomePage.currentUser.getUID())){
                return false;
            }
        }
        return true;
    }
}
