package com.example.askandteach.courseDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askandteach.AsyncTaskLoadImage;
import com.example.askandteach.MainActivity;
import com.example.askandteach.R;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.RegisterCourse;
import com.example.askandteach.models.RegisterCourseResp;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailActivity extends AppCompatActivity {

    TextView tvName, price, time, skill, district, city, description, tvNumberStudent;
    Button btnRegisterCourse;
    String course_id;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        addControls();
        addEvents();
    }

    private void addControls(){
        tvName = findViewById(R.id.tvName);
        price = findViewById(R.id.tvPriceClassDetail);
        time = findViewById(R.id.tvTimeClassDetail);
        skill = findViewById(R.id.tvSkillClassDetail);
        district = findViewById(R.id.tvDistrictClassDetail);
        city = findViewById(R.id.tvCityClassDetail);
        description = findViewById(R.id.tvDescriptionClassDetail);
        tvNumberStudent = findViewById(R.id.txtNumberStudent);
        btnRegisterCourse = findViewById(R.id.btnRegisterCourse);
        avatar = findViewById(R.id.ivCourse);

        Course course = (Course) getIntent().getSerializableExtra("COURSE");

        new AsyncTaskLoadImage(avatar).execute(course.getCoverImage());
        course_id = course.getId().toString();
        price.setText(course.getPrice().toString());
        tvName.setText(course.getUser().getUsername());
        time.setText(course.getUptime());
        skill.setText(course.getSkill());
        district.setText(course.getDistrict());
        city.setText(course.getCity());
        description.setText(course.getDescription());
        tvNumberStudent.setText(course.getAmountStudent().toString());

    }

    private void addEvents(){

        btnRegisterCourse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
                RegisterCourse res = new RegisterCourse();
                res.setCourseId(course_id);

                Call<RegisterCourseResp> call = service.registerCourse(MainActivity.getTokenValue(), res);

                call.enqueue(new Callback<RegisterCourseResp>() {
                    @Override
                    public void onResponse(Call<RegisterCourseResp> call, Response<RegisterCourseResp> response) {
                        Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(Call<RegisterCourseResp> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }



    public static void startCourseDetail(Context context, Course course){
        Intent intent = new Intent(context, CourseDetailActivity.class);
        intent.putExtra("COURSE", course);
        context.startActivity(intent);
    }
}
