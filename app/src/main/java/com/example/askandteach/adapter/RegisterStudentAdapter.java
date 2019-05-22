package com.example.askandteach.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.askandteach.AsyncTaskLoadImage;
import com.example.askandteach.ItemClickListener;
import com.example.askandteach.R;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.StudentList;
import com.example.askandteach.models.User;
import com.example.askandteach.profile.ViewProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class RegisterStudentAdapter extends RecyclerView.Adapter<RegisterStudentAdapter.MyViewHolder> {
    private List<User> registerStudents;
    private ItemClickListener callback;
    private Activity mActivity;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView avatar;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtName);
            avatar = (ImageView) view.findViewById(R.id.avatar);
        }

    }


    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.callback = itemClickListener;
    }

    public RegisterStudentAdapter(Activity mActivity, ArrayList<User> registerStudents) {
        this.registerStudents = registerStudents;
        this.mActivity = mActivity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.register_student_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final User u = registerStudents.get(position);
        holder.name.setText(u.getUsername());
        new AsyncTaskLoadImage(holder.avatar).execute(u.getProfileImage());
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(position);
            }
        });

        holder.name.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfileActivity.start(mActivity, u.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.registerStudents.size();
    }

}
