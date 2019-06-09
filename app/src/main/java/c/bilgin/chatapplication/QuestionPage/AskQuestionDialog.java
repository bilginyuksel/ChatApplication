package c.bilgin.chatapplication.QuestionPage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;

public class AskQuestionDialog extends Dialog {

    private TextView txtInterest;
    private Button addInterest,addQuestion;
    private EditText etQuestion;
    private Spinner spnInterest;
    private Context mContext;
    private ArrayList<String> arr = new ArrayList<>();

    public AskQuestionDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_add_question);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);

        txtInterest = (TextView)findViewById(R.id.showInterest);
        addInterest = (Button)findViewById(R.id.btnAddInterest);
        addQuestion = (Button)findViewById(R.id.btnAddQuestion);
        etQuestion = (EditText)findViewById(R.id.etQuestion);
        spnInterest = (Spinner)findViewById(R.id.spnInterest);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Artificial Intelligence");
        spinnerArray.add("Cyber Security");
        spinnerArray.add("Java");
        spinnerArray.add("Python");
        spinnerArray.add("Virtual Reality");
        spinnerArray.add("Android");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                mContext, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnInterest.setAdapter(adapter);
        final ArrayList<String> i = new ArrayList<>();

        addInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.add(spnInterest.getSelectedItem().toString());
                String interest = "";
                for(String s : i)
                    interest+= "#"+s+" ";
                txtInterest.setText(interest);
            }
        });

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = new Question(etQuestion.getText().toString(),i, HomePage.currentUser);
                new FirebaseQuestion().addData(q,q.getUid());

                Toast.makeText(mContext, "Question added successfully.", Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
    }


}
