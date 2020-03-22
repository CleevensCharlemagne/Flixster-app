package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    int movieId;
    String backdropPath;
    String posterPath;
    String overview;
    String title;
    String date;
    double rating;

    // Empty constructor needed by Parceler library
    public Movie(){}

    public Movie(JSONObject jso) throws JSONException {

        backdropPath = jso.getString("backdrop_path");
        posterPath = jso.getString("poster_path");
        overview = jso.getString("overview");
        title = jso.getString("title");
        rating = jso.getDouble("vote_average");
        date = jso.getString("release_date");
        movieId = jso.getInt("id");

    }

    public static List<Movie> fromJsonArray (JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }

        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public double getRating() {
        return rating;
    }

    public String getDate() {
        return String.format("Release date : %s", date);
    }

    public int getMovieId() {
        return movieId;
    }
}
