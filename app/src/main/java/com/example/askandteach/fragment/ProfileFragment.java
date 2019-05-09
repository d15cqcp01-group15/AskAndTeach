package com.example.askandteach.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.askandteach.Authentication;
import com.example.askandteach.EventDetail.EventDetail;
import com.example.askandteach.ItemClickListener;
import com.example.askandteach.MainActivity;
import com.example.askandteach.R;
import com.example.askandteach.adapter.EventsAdapter;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.Event;
import com.example.askandteach.models.Profile;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends FragmentFactory {

    private Profile profile;
    private Button logout;
    private boolean done_load_profile;


    private TextView txtName, txtIntro, txtPhoneNumber, txtBirthDay, txtNumberStudent, txtNumberCourse;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getProfile();
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        addControls(view);
        addEvents();
        return view;
    }

    private void addControls(View view){
        logout = view.findViewById(R.id.btnLogout);
        txtName = view.findViewById(R.id.txtName);
        txtIntro = view.findViewById(R.id.txtIntro);
        txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);
        txtBirthDay = view.findViewById(R.id.txtBirthDay);
        txtNumberStudent = view.findViewById(R.id.txtNumberStudent);
        txtNumberCourse = view.findViewById(R.id.txtNumberCourse);
    }


    private void addEvents(){
        if (profile != null) {
            logout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = getContext().getSharedPreferences("userdefault", Context.MODE_PRIVATE);
                    preferences.edit().remove("token_value").commit();
                    preferences.edit().remove("user_id").commit();
                    MainActivity.setTokenValue("");
                    getActivity().onBackPressed();
                }
            });
        }
    }

    private void getProfile(){
        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<Profile> call = service.getProfile(MainActivity.getUser_id());

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                String x = String.valueOf(0);
                profile = response.body();
                txtName.setText(profile.getUsername());
                txtIntro.setText(profile.getSelfIntroduce());
                txtPhoneNumber.setText("09345454656");
                txtBirthDay.setText(profile.getBirthday());
                txtNumberStudent.setText(profile.getJoinedEvent().toString());
                txtNumberCourse.setText(profile.getOpenedClass().toString());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                //Handle failure
            }
        });
    }

}
