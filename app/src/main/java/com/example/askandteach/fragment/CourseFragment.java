package com.example.askandteach.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askandteach.ItemClickListener;
import com.example.askandteach.R;
import com.example.askandteach.adapter.CoursesAdapter;
import com.example.askandteach.courseDetail.CourseDetailActivity;
import com.example.askandteach.createCourse.CreateCourseActivity;
import com.example.askandteach.fragment.adapter.CustomSpinnerAdapter;
import com.example.askandteach.models.Course;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CourseFragment extends FragmentFactory implements OnClickListener {

    private TextView tvCity;
    private Spinner city;

    private TextView tvDistrict;
    private Spinner district;

    private TextView tvSkill;
    private Spinner skill;

    private RecyclerView recyclerView;
    private CoursesAdapter mAdapter;
    List<Course> courses = new ArrayList<>();

    public CourseFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        return new CourseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCity= view.findViewById(R.id.tvCity);
        city = (Spinner) view.findViewById(R.id.cbCCity);
        List<String> cities = Arrays.asList(getContext().getResources().getStringArray(R.array.cities));
        tvCity.setText(cities.get(0));
        CustomSpinnerAdapter adapterCities = new CustomSpinnerAdapter(getActivity(), cities, new CustomSpinnerAdapter.ISpinnerCallback(){
            @Override
            public void onItemClicked(String text) {
                hideSpinner(city);
                tvCity.setText(text);
            }
        });

        city.setAdapter(adapterCities);

        tvDistrict= view.findViewById(R.id.tvDistrict);
        district = (Spinner) view.findViewById(R.id.cbCDistrict);
        List<String> districts = Arrays.asList(getContext().getResources().getStringArray(R.array.districts));
        tvDistrict.setText(districts.get(0));
        CustomSpinnerAdapter adapterDistrict = new CustomSpinnerAdapter(getActivity(), cities, new CustomSpinnerAdapter.ISpinnerCallback(){
            @Override
            public void onItemClicked(String text) {
                hideSpinner(district);
                tvDistrict.setText(text);
            }
        });
        district.setAdapter(adapterCities);

        tvSkill= view.findViewById(R.id.tvSkill);
        skill = (Spinner) view.findViewById(R.id.cbCSkills);
        List<String> skills = Arrays.asList(getContext().getResources().getStringArray(R.array.skills));
        tvSkill.setText(skills.get(0));
        CustomSpinnerAdapter skillAdapter = new CustomSpinnerAdapter(getActivity(), cities, new CustomSpinnerAdapter.ISpinnerCallback(){
            @Override
            public void onItemClicked(String text) {
                hideSpinner(skill);
                tvSkill.setText(text);
            }
        });
        skill.setAdapter(adapterCities);

        initListener();

        recyclerView = (RecyclerView) view.findViewById(R.id.classRecycleView);
        mAdapter = new CoursesAdapter(courses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                CourseDetailActivity.startCourseDetail(getActivity(), courses.get(position));
            }
        });

        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<List<Course>> call = service.doGetCourses();

        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                courses.clear();
                courses.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), courses.size()+"111", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getActivity(), courses.size()+"111", Toast.LENGTH_SHORT);
            }
        });

        view.findViewById(R.id.btnTaoClass).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateCourseActivity.start(getContext());
            }
        });
    }

    private void initListener() {
        tvCity.setOnClickListener(this);
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
    }
}