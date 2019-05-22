package com.example.askandteach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateEvent {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("open_time")
    @Expose
    private Long open_time;

@SerializedName("city")
@Expose
private String city;
@SerializedName("district")
@Expose
private String district;
@SerializedName("address")
@Expose
private String address;
@SerializedName("price")
@Expose
private Integer price;
@SerializedName("description")
@Expose
private String description;
@SerializedName("uptime")
@Expose
private String uptime;

    public CreateEvent(String title, String city, String district, String address, Integer price, String description, String uptime, Long open_time) {
        this.title = title;
        this.city = city;
        this.district = district;
        this.address = address;
        this.price = price;
        this.description = description;
        this.uptime = uptime;
        this.open_time = open_time;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getOpen_time() {
        return open_time;
    }

    public void setOpen_time(Long open_time) {
        this.open_time = open_time;
    }
}