package com.example.sampleapidesign.Models;

public class UserProfile {

    private int id;
    private String name, password, imageurl;

    public UserProfile(int id, String name, String password, String imageurl) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.imageurl = imageurl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getImageurl() {
        return imageurl;
    }
}
