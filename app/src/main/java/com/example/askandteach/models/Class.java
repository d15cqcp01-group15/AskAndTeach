package com.example.askandteach.models;

import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private String name, price, time, skills;

    public Class() {
    }

    @RequiresApi(api = VERSION_CODES.O)
    public Class(String name, String price, String time, String[] skills) {
        this.name = name;
        this.price = price;
        this.time = time;
        this.skills = TextUtils.join(" ,  ", skills); ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
