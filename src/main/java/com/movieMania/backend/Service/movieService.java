package com.movieMania.backend.Service;

import com.movieMania.backend.Entity.movie;
import com.movieMania.backend.Entity.movieUpdateData;

import java.util.List;

public interface movieService {

    String addMovie(movie movie);
    movie[] getAllMovies();
    List<movie> getByCategory(String category);
    List<movie> getByName(String name);
    String updateMovie(movieUpdateData movieUpdateData);
    List<movie> getTopRated();
    List<String> getCategories();
    String deleteMovie(Long id);
    movie getById(Long id);
    List<movie> getMoviesByIds(List<Long> ids);
    List<movie> getMoviesWithoutIds(List<Long> ids);

    Long getLatestMovieId();

}
