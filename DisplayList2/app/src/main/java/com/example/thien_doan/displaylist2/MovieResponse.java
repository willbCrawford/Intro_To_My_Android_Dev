package com.example.thien_doan.displaylist2;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {

    @SerializedName("Search")
    private List<Movie> movies;

    @NonNull
    public List<Movie> getMovies() {
        if (movies != null) {
            return movies;
        } else {
            return new ArrayList<Movie>();
        }
    }
}
