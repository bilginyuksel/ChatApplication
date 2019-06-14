package c.bilgin.chatapplication.QuestionPage;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.R;

public class QuestionAdapter extends ArrayAdapter<Question> {

    private List<Question> questions;
    private LayoutInflater inflater;
    private Context mContext;
    private AdapterView.OnItemClickListener onItemClickListener;
    private ViewHolder holder;


    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private class ViewHolder{
        TextView txtQuestion,txtInterests,txtUser,txtAnsCount;
        ImageButton btnVote,btnAnswer,btnFollow;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.card_question,null);
            holder = new ViewHolder();
            holder.txtQuestion = (TextView)convertView.findViewById(R.id.txtQuestion);
            holder.txtInterests = (TextView)convertView.findViewById(R.id.txtInterest);
            holder.txtUser = (TextView)convertView.findViewById(R.id.txtUserAsks);
            holder.txtAnsCount = (TextView)convertView.findViewById(R.id.txtAnswerCount);
            holder.btnVote = (ImageButton)convertView.findViewById(R.id.imgButtonVote);
            holder.btnAnswer = (ImageButton)convertView.findViewById(R.id.imgButtonAnswer);
            holder.btnFollow = (ImageButton)convertView.findViewById(R.id.imgButtonFollowo);


            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        final Question q = questions.get(position);

        if(q!=null){
            holder.txtQuestion.setText(q.getQuestion());
            String interest = "";
            if(q.getInterest()!=null){
                for(String s:q.getInterest())
                    interest+=s+", ";

                holder.txtInterests.setText(interest);
                holder.txtUser.setText(q.getAsker().getName() +" "+q.getAsker().getSurname());
                holder.txtAnsCount.setText(q.getAns()!=null?q.getAns().size() +" Answers":"No answer yet.");
            }



        }

        holder.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update rate here...
                q.giveAVote();
                new FirebaseQuestion().updateRate(q);
                Toast.makeText(mContext, "Question voted!", Toast.LENGTH_SHORT).show();

            }
        });
        holder.btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAnswered(q)){
                    Dialog d = new AnswerDialog(mContext,q);
                    d.show();
                }else{
                    Toast.makeText(mContext, "You've already answered to that question!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseFollow().addData(q,q.getUid());
                Toast.makeText(mContext, "Question followed!", Toast.LENGTH_SHORT).show();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null) onItemClickListener.onItemClick(null,v,position,-1);
            }
        });


        /*btn clicks here..*/

        return convertView;
    }

    private boolean isAnswered(Question q){
        for(Answer a:q.getAns().values()){
            if(a.getAnswerer().getUID().equals(HomePage.currentUser.getUID())){
                return false;
            }
        }
        return true;
    }
}
