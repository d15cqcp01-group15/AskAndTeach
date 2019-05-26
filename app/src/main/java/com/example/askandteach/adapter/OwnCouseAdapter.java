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
import android.widget.Toast;

import com.example.askandteach.AsyncTaskLoadImage;
import com.example.askandteach.ItemClickListener;
import com.example.askandteach.MainActivity;
import com.example.askandteach.R;
import com.example.askandteach.course.CourseManage;
import com.example.askandteach.models.Course;
import com.example.askandteach.profile.ViewProfileActivity;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnCouseAdapter extends RecyclerView.Adapter<OwnCouseAdapter.MyViewHolder> {
    private List<Course> courses;
    private ItemClickListener callback;
    private Activity mActivity;
    public ItemClickListener closeCourseCallBack;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, time, skill, district, numberRegister, status;
        public String city;
        public ImageView avatar;
        public Button btnCloseCourse;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtName);
            time = (TextView) view.findViewById(R.id.txtTime);
            price = (TextView) view.findViewById(R.id.txtPrice);
            skill = (TextView) view.findViewById(R.id.txtSkills);
            district = (TextView) view.findViewById(R.id.txtdistrict);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            numberRegister = view.findViewById(R.id.txtNumberRegister);
            status = view.findViewById(R.id.txtStatus);
            btnCloseCourse = view.findViewById(R.id.btnClose);
        }

    }


    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.callback = itemClickListener;
    }

    public void setCloseCourseCallback(ItemClickListener itemClickListener){
        this.closeCourseCallBack = itemClickListener;
    }

    public OwnCouseAdapter(Activity activity, List<Course> courses) {
        this.courses = courses;
        this.mActivity = activity;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.own_course_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Course cl = courses.get(position);
        holder.name.setText(cl.getUser().getUsername());
        holder.price.setText(String.valueOf(cl.getPrice()) + " (đ)");
        holder.time.setText(cl.getUptime());
        holder.skill.setText(cl.getSkill());
        holder.district.setText(cl.getDistrict());
        holder.city = cl.getCity();
        holder.numberRegister.setText(String.valueOf(cl.getAmountStudent()));
        new AsyncTaskLoadImage(holder.avatar).execute(cl.getCoverImage());
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(position);
            }
        });

        holder.name.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfileActivity.start(mActivity, cl.getUser().getId());
            }
        });

        if(cl.getState()==false){
            holder.btnCloseCourse.setVisibility(View.INVISIBLE);
            holder.status.setText("close");
        }
        else {

            holder.status.setText("openning");

        }

        holder.btnCloseCourse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeCourseCallBack.onClick(cl.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.courses.size();
    }

}
