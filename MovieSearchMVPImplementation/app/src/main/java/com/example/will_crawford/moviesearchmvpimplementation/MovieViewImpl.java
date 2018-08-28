package com.example.will_crawford.moviesearchmvpimplementation;

import java.util.List;

public class MovieViewImpl implements MovieView {

    private MoviesAdapter moviesAdapter;

    public MovieViewImpl(MoviesAdapter moviesAdapter) {
        this.moviesAdapter = moviesAdapter;
    }

    @Override
    public void updateMovie(List<Movie> movies) {
        moviesAdapter.setMovies(movies);
    }
}
