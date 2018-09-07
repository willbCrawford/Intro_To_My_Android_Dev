package com.example.will_crawford.ms_fragment;

import java.util.List;

interface DisplayMovieView {

    void updateMovieInfo(MovieInfoResponse movieInfoResponse, List<Review> reviews);
}
