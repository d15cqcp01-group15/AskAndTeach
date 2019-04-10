package com.example.askandteach.createCourse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.askandteach.R;
import com.example.askandteach.courseDetail.CourseDetailActivity;
import com.example.askandteach.models.Course;

public class CreateCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_course);
    }

    public static void start(Context context){
        Intent intent = new Intent(context, CreateCourseActivity.class);
        context.startActivity(intent);
    }
}
