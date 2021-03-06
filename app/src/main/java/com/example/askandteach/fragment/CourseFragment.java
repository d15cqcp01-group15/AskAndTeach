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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askandteach.Authentication;
import com.example.askandteach.ItemClickListener;
import com.example.askandteach.MainActivity;
import com.example.askandteach.R;
import com.example.askandteach.adapter.CoursesAdapter;
import com.example.askandteach.course.CourseDetailActivity;
import com.example.askandteach.course.CreateCourseActivity;
import com.example.askandteach.adapter.CustomSpinnerAdapter;
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

    private Button btnCreateCourse;

    private RecyclerView recyclerView;
    private CoursesAdapter mAdapter;
    List<Course> originalCourses = new ArrayList<>();
    List<Course> filterCourse = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        addControls(view);
        loadCourse();
        addEvents();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCourse();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void addEvents() {
        tvCity.setOnClickListener(this);
        tvDistrict.setOnClickListener(this);
        tvSkill.setOnClickListener(this);

        mAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                CourseDetailActivity.startCourseDetail(getActivity(), filterCourse.get(position).getId());
            }
        });

        btnCreateCourse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.getTokenValue() == ""){
                    Authentication.start(getContext());
                }
                else{
                    CreateCourseActivity.start(getContext());
                }
            }
        });
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

    private void loadCourse(){
        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<List<Course>> call = service.doGetCourses();

        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {

                ArrayList<Course> unfilter = (ArrayList<Course>) response.body();
                originalCourses.clear();

                if (unfilter !=null) {
                    for (Course course : unfilter) {
                        if (course.getStatus().equals("openning")) {
                            originalCourses.add(course);

                        }
                    }

                    filterCourse.clear();
                    filterCourse.addAll(originalCourses);
                    mAdapter.notifyDataSetChanged();
                }
                filterCourse.addAll(originalCourses);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT);
            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v == tvCity){
            city.performClick();
        }
        else if (v == tvDistrict){
            district.performClick();
        }
        else if (v == tvSkill){
            skill.performClick();
        }
    }

    public void addControls(View view){
        btnCreateCourse = view.findViewById(R.id.btnTaoClass);
        tvCity= view.findViewById(R.id.tvCity);
        city = (Spinner) view.findViewById(R.id.cbCCity);
        List<String> cities = Arrays.asList(getContext().getResources().getStringArray(R.array.cities));
        tvCity.setText(cities.get(0));
        CustomSpinnerAdapter adapterCities = new CustomSpinnerAdapter(getActivity(), cities, new CustomSpinnerAdapter.ISpinnerCallback(){
            @Override
            public void onItemClicked(String text) {
                hideSpinner(city);
                tvCity.setText(text);
                filterCity(text);
            }
        });

        city.setAdapter(adapterCities);

        tvDistrict= view.findViewById(R.id.tvDistrict);
        district = (Spinner) view.findViewById(R.id.cbCDistrict);
        List<String> districts = Arrays.asList(getContext().getResources().getStringArray(R.array.districts));
        tvDistrict.setText(districts.get(0));
        CustomSpinnerAdapter adapterDistrict = new CustomSpinnerAdapter(getActivity(), districts, new CustomSpinnerAdapter.ISpinnerCallback(){
            @Override
            public void onItemClicked(String text) {
                hideSpinner(district);
                tvDistrict.setText(text);
                filterDistrict(text);
            }
        });
        district.setAdapter(adapterDistrict);

        tvSkill= view.findViewById(R.id.tvSkill);
        skill = (Spinner) view.findViewById(R.id.cbCSkills);
        List<String> skills = Arrays.asList(getContext().getResources().getStringArray(R.array.skills));
        tvSkill.setText(skills.get(0));
        CustomSpinnerAdapter skillAdapter = new CustomSpinnerAdapter(getActivity(), skills, new CustomSpinnerAdapter.ISpinnerCallback(){
            @Override
            public void onItemClicked(String text) {
                hideSpinner(skill);
                tvSkill.setText(text);
                filterSkill(text);

            }
        });
        skill.setAdapter(skillAdapter);
        recyclerView = (RecyclerView) view.findViewById(R.id.classRecycleView);
        mAdapter = new CoursesAdapter(getActivity(), filterCourse);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }


    private void filterCity(String filter){
        filterCourse.clear();
        for(Course c : originalCourses){
            if((c.getCity().equalsIgnoreCase(filter) || filter.equalsIgnoreCase("all")) &&
                    (c.getDistrict().equalsIgnoreCase(tvDistrict.getText().toString()) ||  tvDistrict.getText().toString().equalsIgnoreCase("all")) &&
                    (c.getSkill().equalsIgnoreCase(tvSkill.getText().toString()) || tvSkill.getText().toString().equalsIgnoreCase("all")))
            {
                filterCourse.add(c);
            }
        }

        mAdapter.notifyDataSetChanged();
    }

    private void filterSkill(String filter){
        filterCourse.clear();
        for(Course c : originalCourses){
            if((c.getSkill().equalsIgnoreCase(filter) || filter.equalsIgnoreCase("all")) &&
                    (c.getDistrict().equalsIgnoreCase(tvDistrict.getText().toString()) || tvDistrict.getText().toString().equalsIgnoreCase("all")) &&
                    (c.getCity().equalsIgnoreCase(tvCity.getText().toString()) ||tvCity.getText().toString().equalsIgnoreCase("all")))
            {
                filterCourse.add(c);
            }
        }

        mAdapter.notifyDataSetChanged();
    }


    private void filterDistrict(String filter){
        filterCourse.clear();
        for(Course c : originalCourses){
            if((c.getDistrict().equalsIgnoreCase(filter) || filter.equalsIgnoreCase("all"))&&
                    (c.getSkill().equalsIgnoreCase(tvSkill.getText().toString()) || tvSkill.getText().toString().equalsIgnoreCase("all")) &&
                    (c.getCity().equalsIgnoreCase(tvCity.getText().toString()) || tvCity.getText().toString().equalsIgnoreCase("all")))
            {
                filterCourse.add(c);
            }
        }

        mAdapter.notifyDataSetChanged();
    }
}
