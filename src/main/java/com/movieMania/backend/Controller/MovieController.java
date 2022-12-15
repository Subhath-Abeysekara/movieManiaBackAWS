package com.movieMania.backend.Controller;

import com.movieMania.backend.Entity.movie;
import com.movieMania.backend.Entity.movieUpdateData;
import com.movieMania.backend.Service.movieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {

    @Autowired
    private com.movieMania.backend.Service.movieService movieService;

    RestTemplate restTemplate = new RestTemplate();

//    movie addMovie(movie movie);
//    List<movie> getAllMovies();
//    List<movie> getByCategory(String category);
//    List<movie> getByName(String name);
//    String updateMovie(movie movie,Long id);
//    List<movie> getTopRated();
//    List<String> getCategories();
//    String deleteMovie(Long id);

    @PostMapping("/addMovie")
    private String addMovie(@RequestBody movie movie){
        movieService.addMovie(movie);
        return "successfully added";
    }

    @PutMapping("/updateMovie")
    private String updateMovie(@RequestBody movieUpdateData movie){
        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/deleteMovie/{id}")
    private String deleteMovie(@PathVariable Long id){
        return movieService.deleteMovie(id);
    }

    @GetMapping("/getMovies")
    private movie[] getAllMovies(){
        return movieService.getAllMovies();
    }


    @GetMapping("/getLatestMovieId")
    private Long getId(){
        return movieService.getLatestMovieId();
    }

    @GetMapping("/getMovie/{id}")
    private movie getMovie(@PathVariable Long id){

        return restTemplate.getForObject("https://movieania.herokuapp.com/movie/getMovie/"+id,movie.class);
    }


    @PostMapping("/getMoviesByID")
    private List<movie> getMoviesByID(@RequestBody List<Long> ids){
        return movieService.getMoviesByIds(ids);
    }

    @PostMapping("/getMoviesWithoutSelect")
    private List<movie> getMoviesWithoutID(@RequestBody List<Long> ids){
        return movieService.getMoviesWithoutIds(ids);
    }

    @GetMapping("/getMovieByCategory/{category}")
    private List<movie> getMoviesByCat(@PathVariable String category){
        return movieService.getByCategory(category);
    }

    @GetMapping("/getMovieByName/{name}")
    private List<movie> getMoviesByName(@PathVariable String name){
        return movieService.getByName(name);
    }

    @GetMapping("/getMovieTop")
    private List<movie> getMovieTop(){
        return movieService.getTopRated();
    }

    @GetMapping("/getCategories")
    private List<String> getMovieCat(){
        return movieService.getCategories();
    }
}
