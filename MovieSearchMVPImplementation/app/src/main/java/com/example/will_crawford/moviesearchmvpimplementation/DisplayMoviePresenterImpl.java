package com.example.will_crawford.moviesearchmvpimplementation;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayMoviePresenterImpl implements DisplayMoviePresenter{

    private List<Review> reviews;
    private DisplayMovieView displayMovieView;

    DisplayMoviePresenterImpl(DisplayMovieView displayMovieView) {
        this.displayMovieView = displayMovieView;
    }

    public void getIMDbInfo(String searchKey){

        OMDbService omDbService = RetrofitClient.getInstance().getMovieService();
        Call<MovieInfoResponse> call = omDbService.getIMDbInfo(searchKey);
        call.enqueue(new Callback<MovieInfoResponse>() {

            @Override
            public void onResponse(@NonNull Call<MovieInfoResponse> call, @NonNull Response<MovieInfoResponse> response) {
                MovieInfoResponse movieInfoResponse = response.body();
                if (response.isSuccessful() && movieInfoResponse != null) {
                    reviews = movieInfoResponse.getReviews();
                    displayMovieView.updateMovieInfo(movieInfoResponse, reviews);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieInfoResponse> call, @NonNull Throwable t) {
                Log.d("Call Failed", t.getMessage());
            }
        });
    }
}
