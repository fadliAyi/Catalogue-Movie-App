package com.dicoding.academies.ayi.cataloguemovie.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ayi on 16/12/17.
 */

public class ApiService {

    public static final String base_url = "https://api.themoviedb.org/3/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
