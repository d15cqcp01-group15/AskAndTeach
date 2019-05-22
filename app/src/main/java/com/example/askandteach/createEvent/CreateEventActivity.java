package com.example.askandteach.createEvent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.askandteach.models.CreateEvent;
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

public class CreateEventActivity extends AppCompatActivity implements OnClickListener {
    private TextView tvCity;
    private Spinner city;

    private EditText edtPrice, edtAddress, edtDes, edtTitle, edtDate, edtTime;

    private TextView tvDistrict;
    private Spinner district;
    private Button btnCreate;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
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
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtDes = (EditText) findViewById(R.id.edtChitiet);
        edtPrice = (EditText) findViewById(R.id.edtHocPhi);
        edtDate = (EditText) findViewById(R.id.edtDate);
        edtTime = (EditText) findViewById(R.id.edtTime);

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
        Date update = new Date();

        long open_time = 0;
        try {
            String open_time_str = edtDate.getText().toString() + " " + edtTime.getText().toString();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            Date date = (Date) formatter.parse(open_time_str);
            open_time = date.getTime();
        }catch (Exception e) {

        }


        CreateEvent createEvent = new CreateEvent(edtTitle.getText().toString(), tvCity.getText().toString(),
                tvDistrict.getText().toString(),
                edtAddress.getText().toString(),
                Integer.parseInt(edtPrice.getText().toString()),
                edtDes.getText().toString(),
                dateFormat.format(update), open_time);

        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);

        String token = MainActivity.getTokenValue();
        Call<CreateEvent> call = service.createEvent(token ,createEvent);

        call.enqueue(new Callback<CreateEvent>() {
            @Override
            public void onResponse(Call<CreateEvent> call, Response<CreateEvent> response) {
                finish();
            }

            @Override
            public void onFailure(Call<CreateEvent> call, Throwable t) {
            }
        });

    }

    public static void start(Context context){
        Intent intent = new Intent(context, CreateEventActivity.class);
        context.startActivity(intent);
    }
}
