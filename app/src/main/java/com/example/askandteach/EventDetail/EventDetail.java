package com.example.askandteach.EventDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.askandteach.R;
import com.example.askandteach.createCourse.CreateCourseActivity;
import com.example.askandteach.models.Event;

public class EventDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
    }

    public static void start(Context context, Event event){
        Intent intent = new Intent(context, EventDetail.class);
        context.startActivity(intent);
    }
}
