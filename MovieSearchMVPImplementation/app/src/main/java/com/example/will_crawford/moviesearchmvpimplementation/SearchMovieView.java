package com.example.will_crawford.moviesearchmvpimplementation;

import android.view.View;

import java.util.List;

public interface SearchMovieView {

    public void updateMovie(List<Movie> movies);

    public void notValidSearch(String searchKey);
}
