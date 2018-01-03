package com.dicoding.academies.ayi.cataloguemovie.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.academies.ayi.cataloguemovie.entity.NewMovieModel;
import com.dicoding.academies.ayi.cataloguemovie.adapter.NowPlayingAdapter;
import com.dicoding.academies.ayi.cataloguemovie.R;
import com.dicoding.academies.ayi.cataloguemovie.entity.ResponseNewMovieModel;
import com.dicoding.academies.ayi.cataloguemovie.service.ApiInterface;
import com.dicoding.academies.ayi.cataloguemovie.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ayi on 16/12/17.
 */

public class NowPlayingFragment extends Fragment {
    private final static String api_key = "99ddbbc08814cee32a1f8b5c631744a1";

    RecyclerView NowPlayingrecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_now_playing, container, false);
        NowPlayingrecyclerView = (RecyclerView) view.findViewById(R.id.rv_now_playing);

        NowPlayingrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NowPlayingrecyclerView.setHasFixedSize(true);

        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);
        Call<ResponseNewMovieModel> call = apiInterface.getNowPlayingMovie(api_key);

        call.enqueue(new Callback<ResponseNewMovieModel>() {
            @Override
            public void onResponse(Call<ResponseNewMovieModel> call, Response<ResponseNewMovieModel> response) {
                List<NewMovieModel> movieModels = response.body().getResults();
                NowPlayingrecyclerView.setAdapter(new NowPlayingAdapter(movieModels, getActivity()));
            }

            @Override
            public void onFailure(Call<ResponseNewMovieModel> call, Throwable t) {

            }
        });

        return view;
    }
}
