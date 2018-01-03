package com.dicoding.academies.ayi.cataloguemovie.service;

import com.dicoding.academies.ayi.cataloguemovie.entity.ResponseNewMovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ayi on 16/12/17.
 */

public interface ApiInterface {
    @GET("movie/now_playing")
    Call<ResponseNewMovieModel> getNowPlayingMovie(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<ResponseNewMovieModel> getUpcomingMovie(@Query("api_key") String apiKey);
}
