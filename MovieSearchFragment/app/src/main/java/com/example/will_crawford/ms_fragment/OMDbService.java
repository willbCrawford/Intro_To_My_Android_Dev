package com.example.will_crawford.ms_fragment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDbService {

    String BASE_URL = "http://www.omdbapi.com";

    @GET("/?apiKey=bbbd7c62")
    Call<MoviesResponse> getMovie(
            @Query("s") String movieTitle
    );


    @GET("/?apiKey=bbbd7c62")
    Call<MovieInfoResponse> getIMDbInfo(
            @Query("i") String imdbID
    );
}