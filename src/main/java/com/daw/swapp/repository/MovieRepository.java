package com.daw.swapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.daw.swapp.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByDetailsDirectorNameContainingIgnoreCase(String directorName);

    List<Movie> findByDurationBetween(int minDuration, int maxDuration);

    List<Movie> findByDetailsDirectorNameContainingIgnoreCaseAndDurationBetween(
            String directorName, Integer minDuration, Integer maxDuration);
}
