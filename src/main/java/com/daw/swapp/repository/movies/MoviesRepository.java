package com.daw.swapp.repository.movies;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daw.swapp.model.movies.Movie;

public interface MoviesRepository extends MongoRepository<Movie, String> {
    List<Movie> findByDetailsDirectorNameContainingIgnoreCase(String directorName);

    List<Movie> findByDurationBetween(int minDuration, int maxDuration);

    List<Movie> findByDetailsDirectorNameContainingIgnoreCaseAndDurationBetween(
            String directorName, Integer minDuration, Integer maxDuration);
}
