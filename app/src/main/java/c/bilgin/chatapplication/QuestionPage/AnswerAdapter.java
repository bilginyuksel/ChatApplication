package c.bilgin.chatapplication.QuestionPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import c.bilgin.chatapplication.R;

public class AnswerAdapter extends ArrayAdapter<Answer> {

    private List<Answer> answers;
    private LayoutInflater inflater;
    private Context mContext;
    private ViewHolder holder;


    private class ViewHolder{
        TextView txtQuestion,txtAnswer,txtAnswerer,txtRate;
        ImageButton btnShare,btnEdit;
    }

    public AnswerAdapter( Context context, List<Answer> objects) {
        super(context, 0, objects);
        this.mContext = context;
        this.answers = objects;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public Answer getItem(int position) {
        return answers.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.card_answer,null);
            holder = new ViewHolder();

            holder.txtAnswer = (TextView)convertView.findViewById(R.id.txtAnswer);
            holder.txtAnswerer = (TextView)convertView.findViewById(R.id.txtAnswerer);
            holder.txtQuestion = (TextView)convertView.findViewById(R.id.txtQuestion);
            holder.txtRate = (TextView)convertView.findViewById(R.id.txtRateCount);
            holder.btnEdit = (ImageButton)convertView.findViewById(R.id.imgButtonEdit);
            holder.btnShare = (ImageButton)convertView.findViewById(R.id.imgButtonShare);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        Answer a = answers.get(position);

        if (a!=null){
            holder.txtAnswer.setText(a.getAnswer());
            holder.txtRate.setText(a.getRate()+" Voter");
            holder.txtAnswerer.setText(a.getAnswerer().getName() +" "+a.getAnswerer().getSurname());
            holder.txtQuestion.setText(a.getQuestion());
        }

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Share button");
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Edit Button");
            }
        });

        return convertView;
    }
}
