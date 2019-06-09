package c.bilgin.chatapplication.NewsHomePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import c.bilgin.chatapplication.R;


public class NewsAdapter extends ArrayAdapter<News> {

    private List<News> news;
    private LayoutInflater inflater;
    private Context mContext;
    private ViewHolder holder;
    private AdapterView.OnItemClickListener onItemClickListener;


    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    private class ViewHolder
    {
        ImageView newsPhoto;
        TextView txtTitle,txtDescription,txtGroup;
    }


    public NewsAdapter(Context context, List<News> objects) {
        super(context, 0, objects);
        this.news = objects;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public News getItem(int position) {
        return news.get(position);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView =inflater.inflate(R.layout.news_card,null);
            holder = new ViewHolder();

            holder.newsPhoto = (ImageView)convertView.findViewById(R.id.imgViewNewsPhoto);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.txtNewsTitle);
            holder.txtDescription = (TextView)convertView.findViewById(R.id.txtNewsDescription);
            holder.txtGroup = (TextView)convertView.findViewById(R.id.txtNewsGroup);

            convertView.setTag(holder);

        }else{
            holder =(ViewHolder)convertView.getTag();
        }

        News n = news.get(position);
        if(n!=null){
            holder.txtTitle.setText(n.getTitle());
            holder.txtDescription.setText(n.getDescription());
            holder.txtGroup.setText(n.getGroup());
            Picasso.with(mContext).load(n.getImage()).fit().into(holder.newsPhoto);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null)onItemClickListener.onItemClick(null,v,position,-1);
            }
        });
        return convertView;
    }
}
