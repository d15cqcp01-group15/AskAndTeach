package com.example.askandteach.retrofit;

import com.example.askandteach.models.Course;
import com.example.askandteach.models.CreateCourse;
import com.example.askandteach.models.Event;
import com.example.askandteach.models.SignInModel;
import com.example.askandteach.models.SignInresponse;
import com.example.askandteach.models.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIInterface {

    @GET("/courses")
    Call<List<Course>> doGetCourses();

    @GET("/events")
    Call<List<Event>> doGetEvents();

    @GET("/topics")
    Call<List<Topic>> doTopic();

    @POST("/courses")
    Call<CreateCourse> createCourse(@Body CreateCourse createCourse);

    @POST("/auth/login")
    Call<SignInresponse> signIn(@Body SignInModel info);

//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
