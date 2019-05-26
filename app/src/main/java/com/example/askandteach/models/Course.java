package com.example.askandteach.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




public class Course implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("price")
    @Expose
    private Float price;
    @SerializedName("uptime")
    @Expose
    private String uptime;
    @SerializedName("skill")
    @Expose
    private String skill;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cover_image")
    @Expose
    private String coverImage;
    @SerializedName("amount_student")
    @Expose
    private Integer amountStudent;
    @SerializedName("list_student")
    @Expose
    private List<Object> listStudent = null;
    @SerializedName("class_opened")
    @Expose
    private Integer classOpened;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("open_time")
    @Expose
    private Float openTime;
    @SerializedName("deadline_of_registration")
    @Expose
    private Double deadlineOfRegistration;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("state")
    @Expose
    private Boolean state;
    @SerializedName("user")
    @Expose
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Integer getAmountStudent() {
        return amountStudent;
    }

    public void setAmountStudent(Integer amountStudent) {
        this.amountStudent = amountStudent;
    }

    public List<Object> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<Object> listStudent) {
        this.listStudent = listStudent;
    }

    public Integer getClassOpened() {
        return classOpened;
    }

    public void setClassOpened(Integer classOpened) {
        this.classOpened = classOpened;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Float getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Float openTime) {
        this.openTime = openTime;
    }

    public Double getDeadlineOfRegistration() {
        return deadlineOfRegistration;
    }

    public void setDeadlineOfRegistration(Double deadlineOfRegistration) {
        this.deadlineOfRegistration = deadlineOfRegistration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}