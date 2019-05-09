package com.example.askandteach.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.askandteach.R;
import com.example.askandteach.adapter.TopicAdapter;
import com.example.askandteach.adapter.CustomSpinnerAdapter;
import com.example.askandteach.models.Topic;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicFragment extends FragmentFactory implements OnClickListener {
    private RecyclerView recyclerView;
    private TopicAdapter mAdapter;
    List<Topic> topics = new ArrayList<>();

    private TextView tvSkill;
    private Spinner skill;

    public TopicFragment() {
        // Required empty public constructor
    }

    public static TopicFragment newInstance(String param1, String param2) {
        TopicFragment fragment = new TopicFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.topicRecycleView);
        mAdapter = new TopicAdapter(topics);
        addControls(view);
        addEvents();

        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<List<Topic>> call = service.doTopic();

        call.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                topics.clear();
                topics.addAll(response.body());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {

            }
        });
    }

    private void addEvents(){
        tvSkill.setOnClickListener(this);
    }

    private void addControls(View view){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        tvSkill= view.findViewById(R.id.tvSkill);
        skill = (Spinner) view.findViewById(R.id.cbCSkills);
        List<String> skills = Arrays.asList(getContext().getResources().getStringArray(R.array.skills));
        tvSkill.setText(skills.get(0));
        CustomSpinnerAdapter skillAdapter = new CustomSpinnerAdapter(getActivity(), skills, new CustomSpinnerAdapter.ISpinnerCallback(){
            @Override
            public void onItemClicked(String text) {
                hideSpinner(skill);
                tvSkill.setText(text);
            }
        });
        skill.setAdapter(skillAdapter);
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

        if (v == tvSkill){
            skill.performClick();
        }
    }


}
