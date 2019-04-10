package com.example.askandteach.courseDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.askandteach.R;
import com.example.askandteach.models.Course;

public class CourseDetailActivity extends AppCompatActivity {

    TextView tvName, price, time, skill, district, city, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        tvName = findViewById(R.id.tvName);
        price = findViewById(R.id.tvPriceClassDetail);
        time = findViewById(R.id.tvTimeClassDetail);
        skill = findViewById(R.id.tvSkillClassDetail);
        district = findViewById(R.id.tvDistrictClassDetail);
        city = findViewById(R.id.tvCityClassDetail);
        description = findViewById(R.id.tvDescriptionClassDetail);

        Course course = (Course) getIntent().getSerializableExtra("COURSE");
        price.setText(course.getPrice().toString());
        tvName.setText(course.getUser().getUsername());
        time.setText(course.getUptime());
        skill.setText(course.getSkill());
        district.setText(course.getDistrict());
        city.setText(course.getCity());
        description.setText(course.getDescription());
    }

    public static void startCourseDetail(Context context, Course course){
        Intent intent = new Intent(context, CourseDetailActivity.class);
        intent.putExtra("COURSE", course);
        context.startActivity(intent);
    }
}
