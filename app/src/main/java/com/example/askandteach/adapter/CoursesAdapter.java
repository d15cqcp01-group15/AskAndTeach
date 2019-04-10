package com.example.askandteach.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askandteach.ItemClickListener;
import com.example.askandteach.R;
import com.example.askandteach.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder> {
    private List<Course> courses;
    private ItemClickListener callback;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, time, skill, district;
        public String city;
        public ImageView avatar;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtName);
            time = (TextView) view.findViewById(R.id.txtTime);
            price = (TextView) view.findViewById(R.id.txtPrice);
            skill = (TextView) view.findViewById(R.id.txtSkills);
            district = (TextView) view.findViewById(R.id.txtdistrict);
            avatar = (ImageView) view.findViewById(R.id.avatar);


        }

    }


    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.callback = itemClickListener;
    }


    public CoursesAdapter(List<Course> courses) {
        this.courses = courses;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Course cl = courses.get(position);
        holder.name.setText(cl.getUser().getUsername());
        holder.price.setText(cl.getPrice().toString());
        holder.time.setText(cl.getUptime());
        holder.skill.setText(cl.getSkill());
        holder.district.setText(cl.getDistrict());
        holder.city = cl.getCity();
        holder.avatar.setImageResource(R.drawable.avatar);
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.courses.size();
    }


}