package c.bilgin.chatapplication.QuestionPage;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import c.bilgin.chatapplication.R;

public class QuestionAdapter extends ArrayAdapter<Question> {

    private List<Question> questions;
    private LayoutInflater inflater;
    private Context mContext;
    private ViewHolder holder;

    private class ViewHolder{
        TextView txtQuestion,txtInterests,txtUser,txtAnsCount;
        ImageButton btnVote,btnAnswer;
    }

    public QuestionAdapter(Context context,List<Question> objects) {
        super(context, 0, objects);
        this.questions = objects;
        this.mContext = context;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public Question getItem(int position) {
        return questions.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.card_question,null);
            holder = new ViewHolder();
            holder.txtQuestion = (TextView)convertView.findViewById(R.id.txtQuestion);
            holder.txtInterests = (TextView)convertView.findViewById(R.id.txtInterest);
            holder.txtUser = (TextView)convertView.findViewById(R.id.txtUserAsks);
            holder.txtAnsCount = (TextView)convertView.findViewById(R.id.txtAnswerCount);
            holder.btnVote = (ImageButton)convertView.findViewById(R.id.imgButtonVote);
            holder.btnAnswer = (ImageButton)convertView.findViewById(R.id.imgButtonAnswer);


            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        final Question q = questions.get(position);

        if(q!=null){
            holder.txtQuestion.setText(q.getQuestion());
            String interest = "";
            for(String s:q.getInterest())
                interest+=s+", ";
            holder.txtInterests.setText(interest);
            holder.txtUser.setText(q.getAsker().getName() +" "+q.getAsker().getSurname());
            holder.txtAnsCount.setText(q.getAnswers()!=null?q.getAnswers().size() +" Answers":"No answer yet.");

        }

        holder.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update rate here...
                q.giveAVote();
                new FirebaseQuestion().updateRate(q);

            }
        });
        holder.btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog d = new AnswerDialog(mContext,q);
                d.show();

            }
        });


        /*btn clicks here..*/

        return convertView;
    }
}
