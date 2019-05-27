package com.example.askandteach.fragment;

import android.Manifest;
import android.Manifest.permission;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.askandteach.AsyncTaskLoadImage;
import com.example.askandteach.Authentication;
import com.example.askandteach.EventDetail.EventDetail;
import com.example.askandteach.
        ItemClickListener;
import com.example.askandteach.MainActivity;
import com.example.askandteach.R;
import com.example.askandteach.adapter.EventsAdapter;
import com.example.askandteach.course.CourseManage;
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
    final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private TextView txtName, txtIntro, txtPhoneNumber, txtBirthDay, txtNumberStudent, txtNumberCourse, txtCourseManagement;
    private ImageView avatar;
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
        txtCourseManagement = view.findViewById(R.id.txtViewCourses);
        avatar = view.findViewById(R.id.avatar);
        avatar.getLayoutParams().height = 200;
        avatar.getLayoutParams().width = 200;
    }


    private void addEvents(){
        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getContext().getSharedPreferences("userdefault", Context.MODE_PRIVATE);
                preferences.edit().remove("token_value").commit();
                preferences.edit().remove("user_id").commit();
                MainActivity.setTokenValue("");
                MainActivity.setUser_id(0);

                Authentication.start(getContext());
//                getActivity().getSupportFragmentManager().beginTransaction().remove(ProfileFragment.this).commit();
            }
        });

        txtPhoneNumber.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = txtPhoneNumber.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phonenumber));


                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setPositiveButton("oK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            requestPermissions(new String[] {permission.CALL_PHONE},
                                    REQUEST_CODE_ASK_PERMISSIONS);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            return;
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                startActivity(callIntent);
            }
        });

        txtCourseManagement.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseManage.startCourseManageMent(getContext());
            }
        });
    }


    private void getProfile(){
        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<Profile> call = service.getProfile(MainActivity.getUser_id());

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                String x = String.valueOf(0);
                profile = response.body();
                if(response.body() != null) {
                    txtName.setText(profile.getUsername());
                    txtIntro.setText(profile.getSelfIntroduce());
                    txtPhoneNumber.setText("09345454656");
                    txtBirthDay.setText(profile.getBirthday());
                    txtNumberStudent.setText(profile.getJoinedEvent().toString());
                    txtNumberCourse.setText(profile.getOpenedClass().toString());
                    new AsyncTaskLoadImage(avatar).execute(profile.getProfileImage());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                //Handle failure
            }
        });
    }

}
