package com.example.will_crawford.moviesearchmvpimplementation;

import android.view.View;

import java.util.List;

public interface SearchMovieView {

    void updateMovie(List<Movie> movies);

    void notValidSearch(String searchKey);
}
