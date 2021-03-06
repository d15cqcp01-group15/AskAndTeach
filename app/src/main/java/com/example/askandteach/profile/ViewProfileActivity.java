package com.example.askandteach.profile;

import android.Manifest;
import android.Manifest.permission;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.askandteach.AsyncTaskLoadImage;
import com.example.askandteach.MainActivity;
import com.example.askandteach.R;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.Profile;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfileActivity extends AppCompatActivity {

    public static final String USER_ID = "USER_ID";
    private TextView txtName, txtIntro, txtPhoneNumber, txtBirthDay, txtNumberStudent, txtNumberCourse;
    private ImageView avatar;
    final int REQUEST_CODE_ASK_PERMISSIONS = 123;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        addControls();
        int user_id = (int) getIntent().getSerializableExtra("USER_ID");
        getProfile(user_id);
    }

    public static void start(Context context, int userId){
        Intent intent = new Intent(context, ViewProfileActivity.class);
        intent.putExtra(USER_ID, userId);
        context.startActivity(intent);
    }

    private void addControls(){
        avatar = findViewById(R.id.avatar);
        avatar.getLayoutParams().height = 200;
        avatar.getLayoutParams().width = 200;
        txtName = findViewById(R.id.txtName);
        txtIntro = findViewById(R.id.txtIntro);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        txtBirthDay = findViewById(R.id.txtBirthDay);
        txtNumberStudent = findViewById(R.id.txtNumberStudent);
        txtNumberCourse = findViewById(R.id.txtNumberCourse);

        txtPhoneNumber.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = txtPhoneNumber.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phonenumber));


                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setPositiveButton("oK", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = VERSION_CODES.M)
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
    }


    private void getProfile(final int userId){
        APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<Profile> call = service.getProfile(userId);

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                String x = String.valueOf(0);
                Profile profile = response.body();

                txtName.setText(profile.getUsername());
                txtIntro.setText(profile.getSelfIntroduce());
                txtPhoneNumber.setText("09345454656");
                txtBirthDay.setText(profile.getBirthday());
                txtNumberStudent.setText(profile.getJoinedEvent().toString());
                txtNumberCourse.setText(profile.getOpenedClass().toString());
                new AsyncTaskLoadImage(avatar).execute(profile.getProfileImage());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                //Handle failure
            }
        });
    }
}
