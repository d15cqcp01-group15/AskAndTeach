package com.example.askandteach.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.askandteach.ItemClickListener;
import com.example.askandteach.R;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.Event;
import com.example.askandteach.models.Topic;
import com.example.askandteach.profile.ViewProfileActivity;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {
    private List<Topic> topics;
    private ItemClickListener callback;
    private Activity mActivity;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, content, time, creator;
        public String city;
        public ImageView avatar;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtTName);
            time = (TextView) view.findViewById(R.id.txtTTime);
            content = (TextView) view.findViewById(R.id.txtTContent);
            creator = (TextView) view.findViewById(R.id.txtTcreator);
        }

    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.callback = itemClickListener;
    }

    public TopicAdapter(Activity mActivity,List<Topic> topics) {
        this.topics = topics;
        this.mActivity = mActivity;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topic_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Topic tp = topics.get(position);
        holder.name.setText(tp.getTitle());
        holder.content.setText(tp.getContent().substring(0, Math.min(tp.getContent().length() - 1, 150))+ "...");
        holder.time.setText(tp.getCreatedAt());
        holder.creator.setText(tp.getCreator().getUsername());
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(position);
            }
        });

        holder.creator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfileActivity.start(mActivity, tp.getCreator().getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.topics.size();
    }


}
