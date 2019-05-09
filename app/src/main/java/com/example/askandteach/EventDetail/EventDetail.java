package com.example.askandteach.EventDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.askandteach.AsyncTaskLoadImage;
import com.example.askandteach.R;
import com.example.askandteach.createCourse.CreateCourseActivity;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.Event;

import java.io.Serializable;

public class EventDetail extends AppCompatActivity {

    TextView tvCreator, txtAddress, txtTime, txtJoined, txtPrice, txtDescripton;
    Button btnRegisterEvent;
    String event_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        addControls();
    }

    public static void start(Context context, Event event){
        Intent intent = new Intent(context, EventDetail.class);
        intent.putExtra("EVENT", event);
        context.startActivity(intent);
    }

    private void addControls(){
        tvCreator = findViewById(R.id.tvName);
        txtAddress = findViewById(R.id.tvPriceClassDetail);
        txtTime = findViewById(R.id.tvTimeClassDetail);
        txtJoined = findViewById(R.id.tvSkillClassDetail);
        txtPrice = findViewById(R.id.tvDistrictClassDetail);
        txtDescripton = findViewById(R.id.tvCityClassDetail);

        Event e = (Event) getIntent().getSerializableExtra("EVENT");

        event_id = e.getId().toString();
        txtAddress.setText(e.getAddress().toString());
        txtTime.setText(e.getUptime());
        txtDescripton.setText(e.getUptime());
        txtJoined.setText(e.getAmountStudent());
        txtPrice.setText(e.getPrice().toString());
    }

}
