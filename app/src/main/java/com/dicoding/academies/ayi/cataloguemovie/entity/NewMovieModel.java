package com.dicoding.academies.ayi.cataloguemovie.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ayi on 16/12/17.
 */

public class NewMovieModel {

    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private String popularity;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("backdrop_path")
    private String banner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return "http://image.tmdb.org/t/p/w185" + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBanner() {
        return "http://image.tmdb.org/t/p/w500" + banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
