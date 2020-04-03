package com.example.sampleapidesign.Api;

import android.content.Intent;

import com.example.sampleapidesign.Models.DefaultResponse;
import com.example.sampleapidesign.Models.LoginResponse;
import com.example.sampleapidesign.Models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("createprofile")
    Call<DefaultResponse> createProfile(
            @Field("name") String name,
            @Field("password") String password,
            @Field("imageurl") String imageUrl
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("name") String name,
            @Field("password") String password
    );

    @GET("allprofiles")
    Call<UsersResponse> getAllProfiles();

    @FormUrlEncoded
    @PUT("updateprofile/{id}")
    Call<LoginResponse> updateProfile(
            @Path("id") int id,
            @Field("name") String name,
            @Field("password") String password,
            @Field("imageurl") String imageurl
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentPasword,
            @Field("newpassword") String newPassword,
            @Field("name") String name
    );


    @DELETE("deleteprofile/{id}")
    Call<DefaultResponse>deleteUser(
            @Path("id") int id
    );

}
