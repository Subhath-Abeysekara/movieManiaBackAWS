package com.movieMania.backend.Entity;

import lombok.Data;

@Data
public class requestResponse {

    request request;
    movieData movie;

    public requestResponse() {
    }

    public com.movieMania.backend.Entity.request getRequest() {
        return request;
    }

    public void setRequest(com.movieMania.backend.Entity.request request) {
        this.request = request;
    }

    public com.movieMania.backend.Entity.movieData getMovie() {
        return movie;
    }

    public void setMovie(com.movieMania.backend.Entity.movieData movie) {
        this.movie = movie;
    }
}
