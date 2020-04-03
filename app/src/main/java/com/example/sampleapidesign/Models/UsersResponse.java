package com.example.sampleapidesign.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {

    private boolean error;
    @SerializedName("all_profiles")
    private List<UserProfile> userProfiles;

    public UsersResponse(boolean error, List<UserProfile> userProfiles) {
        this.error = error;
        this.userProfiles = userProfiles;
    }

    public boolean isError() {
        return error;
    }

    public List<UserProfile> getUserProfiles() {
        return userProfiles;
    }
}
