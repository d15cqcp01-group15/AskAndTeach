package com.example.askandteach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInresponse {

@SerializedName("token")
@Expose
private String token;

@SerializedName("user_id")
@Expose
private int user_id;

@SerializedName("exp")
@Expose
private String exp;
@SerializedName("username")
@Expose
private String username;

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public int getUser_id() {
    return user_id;
}

public void setUser_id(int user_id) {
    this.user_id = user_id;
}

public String getExp() {
return exp;
}

public void setExp(String exp) {
this.exp = exp;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

}