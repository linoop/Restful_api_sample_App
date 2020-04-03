package com.example.sampleapidesign.Models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    private boolean error;
    private String message;
    @SerializedName("userprofile")
    private UserProfile userProfile;

    public LoginResponse(boolean error, String message, UserProfile userProfile) {
        this.error = error;
        this.message = message;
        this.userProfile = userProfile;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
