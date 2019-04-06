package com.example.askandteach;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.askandteach.fragment.CourseFragment;
import com.example.askandteach.fragment.FragmentFactory;
import com.example.askandteach.fragment.EventFragment;
import com.example.askandteach.fragment.ProfileFragment;
import com.example.askandteach.fragment.TopicFragment;

public class MainActivity extends AppCompatActivity implements FragmentFactory.OnFragmentInteractionListener{

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigation;
    private ViewPagerAdapter pagerAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_class:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_group:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_topic:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_profile:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        addTabs();
    }

    private void addTabs(){
        pagerAdapter.addFrag(new CourseFragment());
        pagerAdapter.addFrag(new EventFragment());
        pagerAdapter.addFrag(new TopicFragment());
        pagerAdapter.addFrag(new ProfileFragment());
        viewPager.setAdapter(pagerAdapter);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
