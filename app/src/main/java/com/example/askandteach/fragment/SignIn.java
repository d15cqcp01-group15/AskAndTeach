package com.example.askandteach.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.askandteach.MainActivity;
import com.example.askandteach.R;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.SignInModel;
import com.example.askandteach.models.SignInresponse;
import com.example.askandteach.retrofit.APIInterface;
import com.example.askandteach.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignIn.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignIn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignIn extends FragmentFactory {

    private Button btnSignin;
    private EditText email;
    private EditText password;

    public SignIn() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SignIn newInstance(String param1, String param2) {
        return new SignIn();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, container, false);
        btnSignin = (Button) view.findViewById(R.id.btnSignIn);
        email = (EditText) view.findViewById(R.id.edtEmail);
        password = (EditText) view.findViewById(R.id.edtMatkhau);
        addControls();
        addEvents();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void addEvents(){
        btnSignin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            APIInterface service = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);

            SignInModel info = new SignInModel(email.getText().toString(), password.getText().toString());
            Call<SignInresponse> call = service.signIn(info);

            call.enqueue(new Callback<SignInresponse>() {
                @Override
                public void onResponse(Call<SignInresponse> call, Response<SignInresponse> response) {
                    SharedPreferences sharedPref = getActivity().getSharedPreferences("userdefault", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    String token_value = response.body().getToken();
                    int user_id= response.body().getUser_id();
                    editor.putString("token_value", token_value);
                    editor.putInt("user_id", user_id);
                    editor.commit();
                    MainActivity.setTokenValue(token_value);
                    MainActivity.setUser_id(user_id);
                    getActivity().onBackPressed();

                }

                @Override
                public void onFailure(Call<SignInresponse> call, Throwable t) {
                }
            });
            }
        });
    }

    private void addControls(){

    }

}
