package com.example.askandteach.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.askandteach.R;
import com.example.askandteach.adapter.ClassRecycleViewAdapter;
import com.example.askandteach.models.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class ClassFragment extends FragmentFactory {

    private RecyclerView recyclerView;
    private ClassRecycleViewAdapter mAdapter;
    List<Class> classList = new ArrayList<>() ;

    public ClassFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClassFragment newInstance(String param1, String param2) {
        return new ClassFragment();
    }

    @TargetApi(VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fakeClass();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.classRecycleView);

        mAdapter = new ClassRecycleViewAdapter(classList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @RequiresApi(api = VERSION_CODES.O)
    public void fakeClass() {
        for (int i = 1; i < 20; i++) {
            String skills[] = {"speaking", "listening"};
            classList.add(new Class("tai", "2000$", new Date().toString(), skills));
        }

    }
}
