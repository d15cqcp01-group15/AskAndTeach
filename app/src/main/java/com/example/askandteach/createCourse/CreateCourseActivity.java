package com.example.askandteach.createCourse;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askandteach.R;
import com.example.askandteach.courseDetail.CourseDetailActivity;
import com.example.askandteach.fragment.adapter.CustomSpinnerAdapter;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.CreateCourse;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCourseActivity extends AppCompatActivity implements OnClickListener {
    private TextView tvCity;
    private Spinner city;

    private TextView tvDistrict;
    private Spinner district;

    private String address = "address ne";
    private String skill = "skill ne";
    private String uptime="2013:04:00";
    private int price= 5000;
    private String description="day la chi tiet ne";
    private Button btnCreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_course);
        initials();
        addControls();
        addEvents();

    }

    private void initials(){
        tvCity =  (TextView) findViewById(R.id.tvCity);
        city = (Spinner) findViewById(R.id.cbCCity);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        tvDistrict = (TextView) findViewById(R.id.tvDistrict);
        district = (Spinner) findViewById(R.id.cbCDistrict);
    }

    private void addControls(){
        List<String> cities = Arrays.asList(getApplication().getResources().getStringArray(R.array.cities));
        tvCity.setText(cities.get(0));
        CustomSpinnerAdapter adapterCities = new CustomSpinnerAdapter(this, cities, new CustomSpinnerAdapter.ISpinnerCallback() {
            @Override
            public void onItemClicked(String text) {
                hideSpinner(city);
                tvCity.setText(text);
            }
        });

        city.setAdapter(adapterCities);

        List<String> districts = Arrays.asList(getApplication().getResources().getStringArray(R.array.districts));
        tvDistrict.setText(districts.get(0));
        CustomSpinnerAdapter adapterDistrict = new CustomSpinnerAdapter(this, districts, new CustomSpinnerAdapter.ISpinnerCallback() {
            @Override
            public void onItemClicked(String text) {
                hideSpinner(district);
                tvDistrict.setText(text);
            }
        });
        district.setAdapter(adapterDistrict);


    }

    private void addEvents(){
        btnCreate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateCourse(v);
            }
        });

        tvCity.setOnClickListener(this);
        tvDistrict.setOnClickListener(this);
    }

    private void hideSpinner(Spinner sp){
        try {
            Method method = Spinner.class.getDeclaredMethod("onDetachedFromWindow");
            method.setAccessible(true);
            method.invoke(sp);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == tvCity){
            city.performClick();
        }
        else if (v == tvDistrict){
            district.performClick();
        }
    }

    public void onCreateCourse(View view) {
        CreateCourse  createCourse = new CreateCourse(tvCity.getText().toString(), tvDistrict.getText().toString(), address, skill, price, description, uptime);
        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<CreateCourse> call = service.createCourse(createCourse);

        call.enqueue(new Callback<CreateCourse>() {
            @Override
            public void onResponse(Call<CreateCourse> call, Response<CreateCourse> response) {
                Toast.makeText(getApplication(), "dung roi ne", Toast.LENGTH_SHORT);

            }

            @Override
            public void onFailure(Call<CreateCourse> call, Throwable t) {
                Toast.makeText(getApplication(), "Sai roi ne", Toast.LENGTH_SHORT);
            }
        });

    }

    public static void start(Context context){
        Intent intent = new Intent(context, CreateCourseActivity.class);
        context.startActivity(intent);
    }
}
