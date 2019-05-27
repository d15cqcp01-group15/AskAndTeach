package com.example.askandteach.course;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.askandteach.ItemClickListener;
import com.example.askandteach.MainActivity;
import com.example.askandteach.R;
import com.example.askandteach.adapter.OwnCouseAdapter;
import com.example.askandteach.models.CloseCourseMessage;
import com.example.askandteach.models.Course;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseManage extends AppCompatActivity {

    private OwnCouseAdapter ownCouseAdapter;
    private RecyclerView ownCourseRecycleView;
    private ArrayList<Course> ownCourse = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.courses_management);
        addControls();
        addEvents();

    }

    private void addControls(){
        ownCourseRecycleView = findViewById(R.id.OwnerCourseRecycleView);
        loadOwnCourse();
        ownCouseAdapter = new OwnCouseAdapter(this, ownCourse);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ownCourseRecycleView.setLayoutManager(mLayoutManager);
        ownCourseRecycleView.setAdapter(ownCouseAdapter);
    }

    private void addEvents(){
        ownCouseAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                CourseDetailActivity.startCourseDetail(getApplicationContext(), ownCourse.get(position).getId());
            }
        });

        ownCouseAdapter.setCloseCourseCallback(new ItemClickListener() {
            @Override
            public void onClick(int course_id) {
                APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
                String token = MainActivity.getTokenValue();
                Call<CloseCourseMessage> call = service.closeCourse(token, course_id);

                call.enqueue(new Callback<CloseCourseMessage>() {
                    @Override
                    public void onResponse(Call<CloseCourseMessage> call, Response<CloseCourseMessage> response) {
                        loadOwnCourse();
                        Toast.makeText(getApplication(), "Đóng khoá học thành công.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<CloseCourseMessage> call, Throwable t) {
                        Toast.makeText(getApplication(), "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private void loadOwnCourse(){
        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        String token = MainActivity.getTokenValue();
        Call<List<Course>> call = service.doGetOwnCourses(token);

        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                ownCourse.clear();
                if(response.body() != null) {
                    ownCourse.addAll(response.body());
                    ownCouseAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getApplication(), "test", Toast.LENGTH_SHORT);
            }
        });

    }

    public static void startCourseManageMent(Context context){
        Intent intent = new Intent(context, CourseManage.class);
        context.startActivity(intent);
    }
}
