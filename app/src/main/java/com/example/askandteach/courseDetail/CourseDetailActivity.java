package com.example.askandteach.courseDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askandteach.R;
import com.example.askandteach.models.Course;

public class CourseDetailActivity extends AppCompatActivity {

    TextView tvName, price, time, skill, district, city, description;
    Button btnRegisterCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        initial();
        addEvents();
        addControls();
    }

    private void initial(){
        tvName = findViewById(R.id.tvName);
        price = findViewById(R.id.tvPriceClassDetail);
        time = findViewById(R.id.tvTimeClassDetail);
        skill = findViewById(R.id.tvSkillClassDetail);
        district = findViewById(R.id.tvDistrictClassDetail);
        city = findViewById(R.id.tvCityClassDetail);
        description = findViewById(R.id.tvDescriptionClassDetail);
        btnRegisterCourse = findViewById(R.id.btnRegisterCourse);

        Course course = (Course) getIntent().getSerializableExtra("COURSE");
        price.setText(course.getPrice().toString());
        tvName.setText(course.getUser().getUsername());
        time.setText(course.getUptime());
        skill.setText(course.getSkill());
        district.setText(course.getDistrict());
        city.setText(course.getCity());
        description.setText(course.getDescription());
    }

    private void addControls(){

    }

    private void addEvents(){
        btnRegisterCourse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO check authentication
                //Send request register course
                Toast.makeText(getApplicationContext(), "111111", Toast.LENGTH_LONG).show();
            }
        });
    }



    public static void startCourseDetail(Context context, Course course){
        Intent intent = new Intent(context, CourseDetailActivity.class);
        intent.putExtra("COURSE", course);
        context.startActivity(intent);
    }
}
