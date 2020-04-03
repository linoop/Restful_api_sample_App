package com.example.sampleapidesign.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sampleapidesign.Models.UserProfile;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "profile_shared_pref";

    private static SharedPrefManager mInstance;

    private Context context;

    private SharedPrefManager(Context ctx) {
        this.context = ctx;
    }

    public static synchronized SharedPrefManager getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(ctx);
        }
        return mInstance;
    }

    public void saveProfile(UserProfile userProfile) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", userProfile.getId());
        editor.putString("name", userProfile.getName());
        editor.putString("password", userProfile.getPassword());
        editor.putString("imageurl", userProfile.getImageurl());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public UserProfile getUser(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserProfile(

                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getString("imageurl", null)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
