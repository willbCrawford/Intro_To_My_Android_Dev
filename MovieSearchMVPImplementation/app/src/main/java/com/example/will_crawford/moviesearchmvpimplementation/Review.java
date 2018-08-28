package com.example.will_crawford.moviesearchmvpimplementation;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("Source")
    String source;

    @SerializedName("Value")
    String rating;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
