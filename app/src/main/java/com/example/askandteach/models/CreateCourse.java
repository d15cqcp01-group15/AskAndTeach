package com.example.askandteach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateCourse {

@SerializedName("city")
@Expose
private String city;
@SerializedName("district")
@Expose
private String district;
@SerializedName("address")
@Expose
private String address;
@SerializedName("skill")
@Expose
private String skill;
@SerializedName("price")
@Expose
private Integer price;
@SerializedName("description")
@Expose
private String description;
@SerializedName("uptime")
@Expose
private String uptime;

    public CreateCourse(String city, String district, String address, String skill, Integer price, String description, String uptime) {
        this.city = city;
        this.district = district;
        this.address = address;
        this.skill = skill;
        this.price = price;
        this.description = description;
        this.uptime = uptime;
    }

    public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getDistrict() {
return district;
}

public void setDistrict(String district) {
this.district = district;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getSkill() {
return skill;
}

public void setSkill(String skill) {
this.skill = skill;
}

public Integer getPrice() {
return price;
}

public void setPrice(Integer price) {
this.price = price;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getUptime() {
return uptime;
}

public void setUptime(String uptime) {
this.uptime = uptime;
}

}