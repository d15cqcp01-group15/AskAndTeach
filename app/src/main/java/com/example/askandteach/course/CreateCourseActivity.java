package com.example.askandteach.course;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.askandteach.MainActivity;
import com.example.askandteach.R;
import com.example.askandteach.adapter.CustomSpinnerAdapter;
import com.example.askandteach.models.CreateCourse;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCourseActivity extends AppCompatActivity implements OnClickListener {
    private TextView tvCity;
    private Spinner city;

    private EditText edtPrice, edtAddress, edtDes;
    private RadioButton rdReading;
    private RadioButton rdListening;
    private RadioButton rdWriting;
    private RadioButton rdSpeaking;

    private TextView tvDistrict;
    private Spinner district;

    private String address = "address ne";
    private String skill = "reading";
    private String uptime="2013:04:00";
    private int price= 5000;
    private String description="day la chi tiet ne";
    private Button btnCreate;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_course);
        addControls();
        addEvents();

    }

    private void addControls(){
        tvCity =  (TextView) findViewById(R.id.tvCity);
        city = (Spinner) findViewById(R.id.cbCCity);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        tvDistrict = (TextView) findViewById(R.id.tvDistrict);
        district = (Spinner) findViewById(R.id.cbCDistrict);
        edtAddress = (EditText) findViewById(R.id.edtDiaChi);
        edtDes = (EditText) findViewById(R.id.edtChitiet);
        edtPrice = (EditText) findViewById(R.id.edtHocPhi);

        rdListening = (RadioButton) findViewById(R.id.radListening);
        rdReading = (RadioButton) findViewById(R.id.radReading);
        rdSpeaking = (RadioButton) findViewById(R.id.radSpeaking);
        rdWriting = (RadioButton) findViewById(R.id.radWriting);

        List<String> cities = new ArrayList<String>(Arrays.asList(getApplication().getResources().getStringArray(R.array.cities)));
        cities.remove(0);
        tvCity.setText(cities.get(0));
        CustomSpinnerAdapter adapterCities = new CustomSpinnerAdapter(this, cities, new CustomSpinnerAdapter.ISpinnerCallback() {
            @Override
            public void onItemClicked(String text) {
                hideSpinner(city);
                tvCity.setText(text);
            }
        });

        city.setAdapter(adapterCities);

        List<String> districts = new ArrayList<String>(Arrays.asList(getApplication().getResources().getStringArray(R.array.districts)));
        districts.remove(0);
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
        String skill = "";
        if (rdWriting.isChecked())
            skill = "writing";
        else if (rdReading.isChecked())
            skill = "reading";
        else if (rdListening.isChecked())
            skill = "listening";
        else if (rdSpeaking.isChecked())
            skill = "speaking";

        Date update = new Date();

        CreateCourse  createCourse = new CreateCourse(tvCity.getText().toString(),
                tvDistrict.getText().toString(),
                edtAddress.getText().toString(),
                skill,
                Integer.parseInt(edtPrice.getText().toString()),
                edtDes.getText().toString(),
                dateFormat.format(update));

        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);

        String token = MainActivity.getTokenValue();
        Call<CreateCourse> call = service.createCourse(token ,createCourse);

        call.enqueue(new Callback<CreateCourse>() {
            @Override
            public void onResponse(Call<CreateCourse> call, Response<CreateCourse> response) {

                finish();
            }

            @Override
            public void onFailure(Call<CreateCourse> call, Throwable t) {
            }
        });

    }

    public static void start(Context context){
        Intent intent = new Intent(context, CreateCourseActivity.class);
        context.startActivity(intent);
    }
}
