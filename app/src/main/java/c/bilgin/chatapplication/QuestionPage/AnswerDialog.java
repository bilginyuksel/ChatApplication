package c.bilgin.chatapplication.QuestionPage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import c.bilgin.chatapplication.R;

public class AnswerDialog extends Dialog {

    private Context mContext;
    private Question q;
    private Answer a;
    public AnswerDialog(Context context,Question q) {
        super(context); this.q = q; this.mContext = context;
    }
    public AnswerDialog(Context context,Answer a ){
        super(context); this.mContext = context; this.a = a; this.q = q;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_answer);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);

        ImageButton btnExit = (ImageButton)findViewById(R.id.btnExit);
        TextView txtSubmit = (TextView)findViewById(R.id.txtSubmit);
        TextView txtQuestion = (TextView)findViewById(R.id.txtQuestion);
        final EditText etAnswer = (EditText)findViewById(R.id.etAnswer);




        if(a!=null){
            etAnswer.setText(a.getAnswer());
            for(Question qu:QuestionChildFragment.arrQuestion){
                if(qu.getUid().equals(a.getQuestion_uid())){
                    q = qu;
                    break;
                }
            }
        }

        txtQuestion.setText(q.getQuestion());
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(a==null){
                   Answer a1 = new Answer(etAnswer.getText().toString(),q.getUid(),q.getQuestion());
                   q.addAns(a1);
                   new FirebaseQuestion().addAnswer(q);
                   new FirebaseAnswer().addData(a1,a1.getUid());
                   if(isFollowed(q)) new FirebaseFollow().addAnswer(q);
               }else{
                   a.setAnswer(etAnswer.getText().toString());
                   q.editAns(a);
                   new FirebaseQuestion().addAnswer(q);
                   new FirebaseAnswer().addData(a,a.getUid());
                   if(isFollowed(q)) new FirebaseFollow().addAnswer(q);
               }
                Toast.makeText(mContext, "Answer uploaded successfully.", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });




    }

    private boolean isFollowed(Question q1){
        for(Question q2 : QuestionChildFragmentFollow.arrQuestion){
            if(q2.getUid().equals(q1.getUid())) return true;
        }
        return false;
    }
}
