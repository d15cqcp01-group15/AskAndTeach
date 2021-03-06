package com.example.askandteach.retrofit;

import com.example.askandteach.models.CloseCourseMessage;
import com.example.askandteach.models.Course;
import com.example.askandteach.models.CourseDetail;
import com.example.askandteach.models.CreateCourse;
import com.example.askandteach.models.CreateEvent;
import com.example.askandteach.models.Event;
import com.example.askandteach.models.Profile;
import com.example.askandteach.models.RegisterCourse;
import com.example.askandteach.models.RegisterCourseResp;
import com.example.askandteach.models.SignInModel;
import com.example.askandteach.models.SignInresponse;
import com.example.askandteach.models.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIInterface {

    @GET("/courses")
    Call<List<Course>> doGetCourses();

    @GET("/user_course")
    Call<List<Course>> doGetOwnCourses(@Header("Authorization") String token);

    @GET("/events")
    Call<List<Event>> doGetEvents();

    @GET("/topics")
    Call<List<Topic>> doTopic();

    @POST("/courses")
    Call<CreateCourse> createCourse(@Header("Authorization") String token, @Body CreateCourse createCourse);

    @POST("/events")
    Call<CreateEvent> createEvent(@Header("Authorization") String token, @Body CreateEvent createEvent);

    @POST("/auth/login")
    Call<SignInresponse> signIn(@Body SignInModel info);

    @POST("/detail_courses")
    Call<RegisterCourseResp> registerCourse(@Header("Authorization") String token, @Body RegisterCourse register);

    @GET("/users/{user_id}")
    Call<Profile> getProfile(@Path("user_id") int user_id);

    @DELETE("/unregister_course")
    Call<String> doUnregisterCourse(@Header("Authorization") String token,@Query("course_id") String course_id);

    @GET("/courses/{course_id}")
    Call<CourseDetail> getCourse(@Path("course_id") int course_id);

    @PUT("/close_course")
    Call<CloseCourseMessage> closeCourse(@Header("Authorization") String token, @Query("course_id") Integer course_id);


//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
