package com.dicoding.academies.ayi.cataloguemovie.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.academies.ayi.cataloguemovie.entity.NewMovieModel;
import com.dicoding.academies.ayi.cataloguemovie.R;
import com.dicoding.academies.ayi.cataloguemovie.entity.ResponseNewMovieModel;
import com.dicoding.academies.ayi.cataloguemovie.adapter.UpComingAdapter;
import com.dicoding.academies.ayi.cataloguemovie.service.ApiInterface;
import com.dicoding.academies.ayi.cataloguemovie.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {

    private final static String api_key = "99ddbbc08814cee32a1f8b5c631744a1";

    RecyclerView UpComingRecyclerView;

    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);

        UpComingRecyclerView = (RecyclerView) view.findViewById(R.id.rv_up_coming);

        UpComingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpComingRecyclerView.setHasFixedSize(true);

        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);
        Call<ResponseNewMovieModel> call1 = apiInterface.getUpcomingMovie(api_key);

        call1.enqueue(new Callback<ResponseNewMovieModel>() {
            @Override
            public void onResponse(Call<ResponseNewMovieModel> call, Response<ResponseNewMovieModel> response) {
                //Clear dlu moviewmodels nnya
                List<NewMovieModel> movieModels;
                movieModels = response.body().getResults();
                UpComingRecyclerView.setAdapter(new UpComingAdapter(movieModels, getActivity()));
            }

            @Override
            public void onFailure(Call<ResponseNewMovieModel> call, Throwable t) {

            }
        });

        return view;
    }

}
