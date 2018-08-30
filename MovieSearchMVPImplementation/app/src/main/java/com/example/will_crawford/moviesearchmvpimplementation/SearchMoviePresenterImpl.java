package com.example.will_crawford.moviesearchmvpimplementation;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMoviePresenterImpl implements SearchMoviePresenter {

    private List<Movie> movies;
    private SearchMovieView searchMovieView;
    private Pattern patternForValidSearches = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);


    SearchMoviePresenterImpl(SearchMovieView searchMovieView){
        this.searchMovieView = searchMovieView;
    }

    @Override
    public void getMovie(String searchKey) {

        if (isSearchKeyValid(searchKey)) {
            showSearchKeyNotValid();
        }
        else {
            OMDbService omDbService = RetrofitClient.getInstance().getMovieService();
            Call<MoviesResponse> call = omDbService.getMovie(searchKey);

            call.enqueue(new Callback<MoviesResponse>() {

                @Override
                public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                    MoviesResponse moviesResponse = response.body();
                    if (response.isSuccessful() && moviesResponse != null) {
                        movies = moviesResponse.getMovies();
                        searchMovieView.updateMovie(movies);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                    Log.d("Call Failed: ", t.getMessage());
                }
            });
        }
    }

    private boolean isSearchKeyValid(String searchKey) {
        Matcher matcherForValidSearches = patternForValidSearches.matcher(searchKey);
        return matcherForValidSearches.find();
    }

    private void showSearchKeyNotValid(){
        searchMovieView.notValidSearch();
    }
}
