package com.daw.swapp.service;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public List<Movie> listMoviesByCustomQuery(String director, Integer minDuration, Integer maxDuration) {
        Integer minDurationParam = Optional.ofNullable(minDuration).orElse(0);
        Integer maxDurationParam = Optional.ofNullable(maxDuration).orElse(Integer.MAX_VALUE);

        Query query = new Query();

        if (director != null && !director.isEmpty()) {
            query.addCriteria(Criteria.where("details.director.name").regex(director, "i"));
        }
        if (minDuration != null || maxDuration != null) {
            query.addCriteria(Criteria.where("duration").gte(minDurationParam).lte(maxDurationParam));
        }

        return mongoTemplate.find(query, Movie.class);
    }

    public List<Movie> listMoviesByQuery(String director, Integer minDuration, Integer maxDuration) {
        Integer minDurationParam = Optional.ofNullable(minDuration).orElse(0);
        Integer maxDurationParam = Optional.ofNullable(maxDuration).orElse(Integer.MAX_VALUE);

        if (director != null && (minDuration != null || maxDuration != null)) {
            return movieRepository.findByDetailsDirectorNameContainingIgnoreCaseAndDurationBetween(director,
                    minDurationParam, maxDurationParam);
        } else if (director != null && minDuration == null && maxDuration == null) {
            return movieRepository.findByDetailsDirectorNameContainingIgnoreCase(director);
        } else if (minDuration != null || maxDuration != null) {
            return movieRepository.findByDurationBetween(minDurationParam, maxDurationParam);
        } else {
            return movieRepository.findAll();
        }
    }

    public List<Movie> listMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> listMoviesByDirectorContains(String director) {
        return movieRepository.findByDetailsDirectorNameContainingIgnoreCase(director);
    }

    public List<Movie> listMoviesByDurationRange(int minDuration, int maxDuration) {
        return movieRepository.findByDurationBetween(minDuration, maxDuration);
    }

    public Document addMovie(AddMovieRequest movieRequest) {
        Document document = Document.parse(movieRequest.getMovieInfo());
        return mongoTemplate.insert(document, "movies");
    }
}
