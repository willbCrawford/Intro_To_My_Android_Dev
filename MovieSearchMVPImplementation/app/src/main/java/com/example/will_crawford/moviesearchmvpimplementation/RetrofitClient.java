package com.example.will_crawford.moviesearchmvpimplementation;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance;
    private Retrofit retrofit;
    private OMDbService OMDbAPI;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(OMDbService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OMDbAPI = retrofit.create(OMDbService.class);
    }

    public static RetrofitClient getInstance() {
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public OMDbService getMovieService() {
        return OMDbAPI;
    }
}
