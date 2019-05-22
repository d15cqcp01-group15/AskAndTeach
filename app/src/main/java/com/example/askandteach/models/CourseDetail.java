package com.example.askandteach.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseDetail {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("price")
@Expose
private Double price;
@SerializedName("uptime")
@Expose
private String uptime;
@SerializedName("skill")
@Expose
private String skill;
@SerializedName("description")
@Expose
private String description;
@SerializedName("student_list")
@Expose
private ArrayList<User> registerStudents = null;
@SerializedName("amount_student")
@Expose
private Integer amountStudent;
@SerializedName("class_opened")
@Expose
private Integer classOpened;
@SerializedName("cover_image")
@Expose
private String coverImage;
@SerializedName("open_time")
@Expose
private Integer openTime;
@SerializedName("district")
@Expose
private String district;
@SerializedName("address")
@Expose
private String address;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("user")
    @Expose
    private User user;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Double getPrice() {
return price;
}

public void setPrice(Double price) {
this.price = price;
}

public String getUptime() {
return uptime;
}

public void setUptime(String uptime) {
this.uptime = uptime;
}

public String getSkill() {
return skill;
}

public void setSkill(String skill) {
this.skill = skill;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public ArrayList<User> getStudentList() {
return registerStudents;
}

public void setStudentList(ArrayList<User> registerStudents) {
this.registerStudents = registerStudents;
}

public Integer getAmountStudent() {
return amountStudent;
}

public void setAmountStudent(Integer amountStudent) {
this.amountStudent = amountStudent;
}

public Integer getClassOpened() {
return classOpened;
}

public void setClassOpened(Integer classOpened) {
this.classOpened = classOpened;
}

public String getCoverImage() {
return coverImage;
}

public void setCoverImage(String coverImage) {
this.coverImage = coverImage;
}

public Integer getOpenTime() {
return openTime;
}

public void setOpenTime(Integer openTime) {
this.openTime = openTime;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}