package com.daw.swapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.daw.swapp.model.AddMovieRequest;
import com.daw.swapp.model.Movie;
import com.daw.swapp.service.MoviesService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @GetMapping("/movies")
    public String showMovies(@RequestParam(defaultValue = "") String director, Model model) {
        List<Movie> movies = moviesService.ListMovies(director);
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movies/add")
    public String showAddMovieForm() {
        return "addMovie";
    }

    @PostMapping("/movies/add")
    public ResponseEntity<?> addMovie(@RequestBody AddMovieRequest movieRequest) {
        try {
            return ResponseEntity.ok(moviesService.addMovie(movieRequest));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding movie: " + e.getMessage());
        }
    }
}