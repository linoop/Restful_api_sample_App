package com.example.sampleapidesign.Api;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://192.168.1.103/SampleApi/public/";

    private static RetrofitClient retrofitClientInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(retrofitClientInstance == null){
            retrofitClientInstance = new RetrofitClient();
        }
        return retrofitClientInstance;
    }


    public Api getApi(){

        return retrofit.create(Api.class);

    }

}
