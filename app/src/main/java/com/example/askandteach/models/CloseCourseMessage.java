package com.example.askandteach.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloseCourseMessage {

@SerializedName("message")
@Expose
private String message;

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}