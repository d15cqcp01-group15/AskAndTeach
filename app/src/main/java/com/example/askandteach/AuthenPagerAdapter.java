package com.example.askandteach;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AuthenPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentsList = new ArrayList<>();

    public AuthenPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentsList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    public void addFrag(Fragment fragment) {
        fragmentsList.add(fragment);
    }
}
