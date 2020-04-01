package com.example.sampleapidesign.Api;

import com.example.sampleapidesign.Models.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("createprofile")
    Call<DefaultResponse>createProfile(
            @Field("name") String name,
            @Field("password") String password,
            @Field("imageurl") String imageUrl
    );
}
