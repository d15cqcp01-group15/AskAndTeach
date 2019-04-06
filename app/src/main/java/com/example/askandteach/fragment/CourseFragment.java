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
import android.widget.Toast;

import com.example.askandteach.ItemClickListener;
import com.example.askandteach.R;
import com.example.askandteach.adapter.CoursesAdapter;
import com.example.askandteach.courseDetail.CourseDetailActivity;
import com.example.askandteach.createCourse.CreateCourseActivity;
import com.example.askandteach.models.Course;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CourseFragment extends FragmentFactory {

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
        Spinner city = (Spinner) view.findViewById(R.id.cbCCity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter);

        Spinner district = (Spinner) view.findViewById(R.id.cbCDistrict);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.districts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(adapter1);

        Spinner skill = (Spinner) view.findViewById(R.id.cbCSkills);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.skills, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skill.setAdapter(adapter2);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
}
