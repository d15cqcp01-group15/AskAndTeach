package com.example.askandteach.models;

import com.example.askandteach.models.Avatar;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("opened_class")
    @Expose
    private Integer openedClass;
    @SerializedName("joined_event")
    @Expose
    private Integer joinedEvent;
    @SerializedName("self_introduce")
    @Expose
    private String selfIntroduce;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getOpenedClass() {
        return openedClass;
    }

    public void setOpenedClass(Integer openedClass) {
        this.openedClass = openedClass;
    }

    public Integer getJoinedEvent() {
        return joinedEvent;
    }

    public void setJoinedEvent(Integer joinedEvent) {
        this.joinedEvent = joinedEvent;
    }

    public String getSelfIntroduce() {
        return selfIntroduce;
    }

    public void setSelfIntroduce(String selfIntroduce) {
        this.selfIntroduce = selfIntroduce;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}