package com.example.thien_doan.displaylist2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(OMDbService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;

    }
}
