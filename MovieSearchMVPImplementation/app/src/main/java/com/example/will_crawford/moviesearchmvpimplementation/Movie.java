package com.example.will_crawford.moviesearchmvpimplementation;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }
}
