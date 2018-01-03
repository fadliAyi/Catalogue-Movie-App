package com.dicoding.academies.ayi.cataloguemovie.entity;

import com.dicoding.academies.ayi.cataloguemovie.entity.NewMovieModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ayi on 16/12/17.
 */

public class ResponseNewMovieModel {
    @SerializedName("results")
    private List<NewMovieModel> results;

    public List<NewMovieModel> getResults() {
        return results;
    }

    public void setResults(List<NewMovieModel> results) {
        this.results = results;
    }



}
