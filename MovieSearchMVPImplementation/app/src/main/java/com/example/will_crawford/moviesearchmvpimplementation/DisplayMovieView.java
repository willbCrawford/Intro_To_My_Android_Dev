package com.example.will_crawford.moviesearchmvpimplementation;

import java.util.List;

public interface DisplayMovieView {

    public void updateMovieInfo(MovieInfoResponse movieInfoResponse, List<Review> reviews);
}
