package c.bilgin.chatapplication.QuestionPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import c.bilgin.chatapplication.Firebase;
import c.bilgin.chatapplication.R;

public class CustomAnswerAdapter extends ArrayAdapter<Answer> {


    private List<Answer> answers;
    private LayoutInflater inflater;
    private Context mContext;
    private ViewHolder holder;
    private Question currentQ;


    private class ViewHolder{
        TextView txtAnswer,txtAnswerer,txtRate;
        ImageButton btnShare,btnVote;
    }

    public CustomAnswerAdapter(Context context, List<Answer> objects, Question q) {
        super(context, 0, objects); this.mContext = context;
        this.answers = objects;
        this.inflater = LayoutInflater.from(mContext);
        this.currentQ = q;
    }


    @Override
    public Answer getItem(int position) {
        return answers.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.card_custom_answer,null);
            holder = new ViewHolder();

            holder.txtAnswer = (TextView)convertView.findViewById(R.id.txtAnswer);
            holder.txtAnswerer = (TextView)convertView.findViewById(R.id.txtAnswerer);
            holder.txtRate = (TextView)convertView.findViewById(R.id.txtRateCount);
            holder.btnShare = (ImageButton)convertView.findViewById(R.id.imgButtonEdit);
            holder.btnVote = (ImageButton)convertView.findViewById(R.id.imgButtonShare);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        final Answer a = answers.get(position);

        if (a!=null){
            holder.txtAnswer.setText(a.getAnswer());
            holder.txtRate.setText(a.getRate()+" Vote");
            holder.txtAnswerer.setText(a.getAnswerer().getName() +" "+a.getAnswerer().getSurname());
        }

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Sorry Share on progress.", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // a.updateRate();
                currentQ.getAns().get(a.getUid()).updateRate();
                new FirebaseAnswer().updateRate(a);
                new FirebaseQuestion().addAnswer(currentQ);
                new FirebaseFollow().addAnswer(currentQ);

                Toast.makeText(mContext, "You voted succesfully.", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


}
