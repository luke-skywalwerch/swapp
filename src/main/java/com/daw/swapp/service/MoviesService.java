package com.daw.swapp.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.daw.swapp.model.AddMovieRequest;
import com.daw.swapp.model.Movie;
import com.daw.swapp.repository.MovieRepository;

@Service
public class MoviesService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> ListMovies(String director) {
        if (director.isEmpty()) {
            return movieRepository.findAll();
        } else {
            return ListMoviesByDirectorContains(director);
        }
    }

    public List<Movie> ListMoviesByDirectorContains(String director) {
        return movieRepository.findByDetailsDirectorNameContainingIgnoreCase(director);
    }

    public Document addMovie(AddMovieRequest movieRequest) {
        Document document = Document.parse(movieRequest.getMovieInfo());
        return mongoTemplate.insert(document, "movies");
    }
}
