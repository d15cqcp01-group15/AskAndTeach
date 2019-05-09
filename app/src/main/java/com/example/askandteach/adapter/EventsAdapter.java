package com.example.askandteach.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.example.askandteach.profile.ViewProfileActivity;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder>{
    private List<Event> events;
    private ItemClickListener callback;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, time, district, joined, creator;
        public ImageView avatar;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtEName);
            district = (TextView) view.findViewById(R.id.txtEDistrict);
            time = (TextView) view.findViewById(R.id.txtETime);
            joined = (TextView) view.findViewById(R.id.txtEJoined);
            creator = (TextView) view.findViewById(R.id.txtEcreator);
        }
    }


    public EventsAdapter(Activity mActivity, List<Event> events) {
        this.events = events;
        this.mActivity = mActivity;

    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.callback = itemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Event e = events.get(position);
        holder.name.setText(e.getTitle());
        holder.time.setText(e.getUptime());
        holder.district.setText(e.getDistrict());
        holder.joined.setText(e.getAmountStudent().toString());
        holder.creator.setText(e.getUser().getUsername());
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(position);
            }
        });

        holder.creator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfileActivity.start(mActivity, e.getUser().getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }
}
