package com.example.askandteach.adapter;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.askandteach.R;
import com.example.askandteach.models.Class;

import java.util.List;

public class ClassRecycleViewAdapter extends RecyclerView.Adapter<ClassRecycleViewAdapter.MyViewHolder>{
    private List<Class> classList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, time, skill;
        public ImageView avatar;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtName);
            time = (TextView) view.findViewById(R.id.txtTime);
            price = (TextView) view.findViewById(R.id.txtPrice);
            skill = (TextView) view.findViewById(R.id.txtSkills);
            avatar = (ImageView) view.findViewById(R.id.avatar);
        }
    }


    public ClassRecycleViewAdapter(List<Class> classList) {
        this.classList = classList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Class cl = classList.get(position);
        holder.name.setText(cl.getName());
        holder.price.setText(cl.getPrice());
        holder.time.setText(cl.getTime());
        holder.skill.setText(cl.getSkills());
        holder.avatar.setImageResource(R.drawable.avatar);
    }

    @Override
    public int getItemCount() {
        return this.classList.size();
    }
}
