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
    public AnswerDialog(Context context,Question q) {
        super(context); this.q = q; this.mContext = context;
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
                Answer a = new Answer(etAnswer.getText().toString(),q.getUid(),q.getQuestion());
                q.addAnswer(a);
                new FirebaseQuestion().addAnswer(q);
                new FirebaseAnswer().addData(a,a.getUid());
                Toast.makeText(mContext, "Answer uploaded successfully.", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });




    }
}
