package com.example.thien_doan.displaylist2;

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
