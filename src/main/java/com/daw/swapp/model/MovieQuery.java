package com.daw.swapp.model;

public class MovieQuery {
    private String director;
    private Integer minDuration;
    private Integer maxDuration;

    public String getMovieQueryDirector() {
        return director;
    }

    public void setMovieQueryDirector(String director) {
        this.director = director;
    }

    public Integer getMovieQueryMinDuration() {
        return minDuration;
    }

    public void setMovieQueryMinDuration(Integer minDuration) {
        this.minDuration = minDuration;
    }

    public Integer getMovieQueryMaxDuration() {
        return maxDuration;
    }

    public void setMovieQueryMaxDuration(Integer maxDuration) {
        this.maxDuration = maxDuration;
    }
}

