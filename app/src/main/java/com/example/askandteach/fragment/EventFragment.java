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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.askandteach.EventDetail.EventDetail;
import com.example.askandteach.ItemClickListener;
import com.example.askandteach.R;
import com.example.askandteach.adapter.EventsAdapter;
import com.example.askandteach.adapter.CustomSpinnerAdapter;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.Event;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventFragment extends FragmentFactory implements OnClickListener{

    private TextView tvCity;
    private Spinner city;

    private TextView tvDistrict;
    private Spinner district;

    private RecyclerView recyclerView;
    private EventsAdapter mAdapter;
    List<Event> originalEvents = new ArrayList<>();
    List<Event> filterEvents = new ArrayList<>();

    public EventFragment() {
        // Required empty public constructor
    }

    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        addControls(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycleView);
        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<List<Event>> call = service.doGetEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                originalEvents.clear();
                originalEvents.addAll(response.body());
                filterEvents.addAll(originalEvents);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

        addEvents();
    }

    public void addControls(View view){
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

        recyclerView = (RecyclerView) view.findViewById(R.id.eventRecycleView);
        mAdapter = new EventsAdapter(filterEvents);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }



    private void addEvents(){
        tvCity.setOnClickListener(this);
        tvDistrict.setOnClickListener(this);

        mAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                EventDetail.start(getActivity(), originalEvents.get(position));
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

    @Override
    public void onClick(View v) {
        if (v == tvCity){
            city.performClick();
        }
        else if (v == tvDistrict){
            district.performClick();
        }
    }

    private void filterCity(String filter){
        filterEvents.clear();
        for(Event e : originalEvents){
            if((e.getCity().equalsIgnoreCase(filter) || filter.equalsIgnoreCase("all")) &&
                    (e.getDistrict().equalsIgnoreCase(tvDistrict.getText().toString()) ||  tvDistrict.getText().toString().equalsIgnoreCase("all")))
            {
                filterEvents.add(e);
            }
        }

        mAdapter.notifyDataSetChanged();
    }

    private void filterDistrict(String filter){
        filterEvents.clear();
        for(Event e : originalEvents){
            if((e.getDistrict().equalsIgnoreCase(filter) || filter.equalsIgnoreCase("all"))&&
                    (e.getCity().equalsIgnoreCase(tvCity.getText().toString()) || tvCity.getText().toString().equalsIgnoreCase("all")))
            {
                filterEvents.add(e);
            }
        }

        mAdapter.notifyDataSetChanged();
    }

}
