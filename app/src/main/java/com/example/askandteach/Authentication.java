package com.example.askandteach;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.TabLayout;

import com.example.askandteach.EventDetail.EventDetail;
import com.example.askandteach.fragment.CourseFragment;
import com.example.askandteach.fragment.EventFragment;
import com.example.askandteach.fragment.FragmentFactory;
import com.example.askandteach.fragment.ProfileFragment;
import com.example.askandteach.fragment.SignIn;
import com.example.askandteach.fragment.SignUp;
import com.example.askandteach.fragment.TopicFragment;
import com.example.askandteach.models.Event;


public class Authentication extends AppCompatActivity implements FragmentFactory.OnFragmentInteractionListener{

    private ViewPager viewPager;
    private AuthenPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        viewPager = (ViewPager) findViewById(R.id.authenPager);
        pagerAdapter = new AuthenPagerAdapter(getSupportFragmentManager());
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        addTabs(tabLayout);
    }

    private void addTabs(TabLayout tabLayout) {
        pagerAdapter.addFrag(new SignIn());
        pagerAdapter.addFrag(new SignUp());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.getTabAt(0).setText("Đăng nhập");
        tabLayout.getTabAt(1).setText("Đăng ký");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, Authentication.class);
        context.startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}