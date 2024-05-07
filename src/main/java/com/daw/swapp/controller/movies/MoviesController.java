package com.daw.swapp.controller.movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.daw.swapp.model.movies.AddMovieRequest;
import com.daw.swapp.model.movies.Movie;
import com.daw.swapp.service.movies.MoviesService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @GetMapping("/movies")
    public String showMovies(@RequestParam(required = false) String director,
            @RequestParam(required = false) Integer minDuration,
            @RequestParam(required = false) Integer maxDuration,
            Model model) {
        List<Movie> movies = moviesService.listMoviesByQuery(director, minDuration, maxDuration);
        moviesService.setMovieModel(model, movies, director, minDuration, maxDuration);

        return "movies/home";
    }

    @GetMapping("/movies/add")
    public String showAddMovieForm() {
        return "movies/addMovie";
    }

    @PostMapping("/movies/add")
    public ResponseEntity<Object> addMovie(@RequestBody AddMovieRequest movieRequest) {
        try {
            return ResponseEntity.ok(moviesService.addMovie(movieRequest));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding movie: " + e.getMessage());
        }
    }
}