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
import com.example.askandteach.models.CourseDetail;
import com.example.askandteach.models.Profile;
import com.example.askandteach.models.RegisterCourse;
import com.example.askandteach.models.RegisterCourseResp;
import com.example.askandteach.models.StudentList;
import com.example.askandteach.profile.ViewProfileActivity;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailActivity extends AppCompatActivity {

    TextView tvName, price, time, skill, district, city, description, tvNumberStudent;
    Button btnRegisterCourse;
    CourseDetail course;

    ImageView avatar;
    private boolean registed = false;

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

        final int course_id = (int) getIntent().getSerializableExtra("COURSE");

        int user_id = MainActivity.getUser_id();
        if(user_id == 0){
            btnRegisterCourse.setVisibility(View.INVISIBLE);

        }
        else{
            getCourse(course_id);
        }

        tvName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfileActivity.start(CourseDetailActivity.this, course.getUser().getId());
            }
        });

    }

    private void addEvents(){

        btnRegisterCourse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);


                if (registed == false) {
                    RegisterCourse res = new RegisterCourse();
                    res.setCourseId(course.getId().toString());

                    Call<RegisterCourseResp> call = service.registerCourse(MainActivity.getTokenValue(), res);

                    call.enqueue(new Callback<RegisterCourseResp>() {
                        @Override
                        public void onResponse(Call<RegisterCourseResp> call, Response<RegisterCourseResp> response) {
                            btnRegisterCourse.setText("Huỹ đăng ký");
                            registed = true;
                        }

                        @Override
                        public void onFailure(Call<RegisterCourseResp> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT);
                        }
                    });
                }
                else{
                    Call<String> call = service.doUnregisterCourse(MainActivity.getTokenValue(), "course_id: course.getId().toString()");

                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            btnRegisterCourse.setText("Đăng ký");
                            registed = false;
                            getCourse(course.getId());
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT);
                        }
                    });
                }
            }
        });
    }


    private void getCourse(int course_id){
        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<CourseDetail> call = service.getCourse(course_id);

        call.enqueue(new Callback<CourseDetail>() {
            @Override
            public void onResponse(Call<CourseDetail> call, Response<CourseDetail> response) {
                String x = String.valueOf(0);
                course = response.body();
                new AsyncTaskLoadImage(avatar).execute(course.getCoverImage());
                price.setText(course.getPrice().toString());
                tvName.setText(course.getName());
                time.setText(course.getUptime());
                skill.setText(course.getSkill());
                district.setText(course.getAddress());
                city.setText(course.getCity());
                description.setText(course.getDescription());
                tvNumberStudent.setText(course.getAmountStudent().toString());

                ArrayList<StudentList> users_info = (ArrayList<StudentList>) course.getStudentList();

                for(StudentList user: users_info){
                    if(user.getId() == MainActivity.getUser_id()){
                        registed = true;
                    }
                }

                if(registed == true){
                    btnRegisterCourse.setText("Huỹ đăng ký");
                }
            }

            @Override
            public void onFailure(Call<CourseDetail> call, Throwable t) {
                //Handle failure
            }
        });
    }

    public static void startCourseDetail(Context context, int course_id){
        Intent intent = new Intent(context, CourseDetailActivity.class);
        intent.putExtra("COURSE", course_id);
        context.startActivity(intent);
    }
}
