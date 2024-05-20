package com.daw.swapp.service.movies;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.daw.swapp.model.movies.AddMovieRequest;
import com.daw.swapp.model.movies.Movie;
import com.daw.swapp.repository.movies.MoviesRepository;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepository movieRepository;

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

    public Document addMovieJson(AddMovieRequest movieRequest) {
        Document document = Document.parse(movieRequest.getMovieInfo());
        return mongoTemplate.insert(document, "movies");
    }

    public Movie addMovie(Movie movieRequest) {
        return movieRepository.save(movieRequest);
    }

    public void setMovieModel(Model model, List<Movie> movies, String director, Integer minDuration,
            Integer maxDuration) {
        model.addAttribute("movies", movies);
        if (director != null && !director.isEmpty()) {
            model.addAttribute("searchDirector", director);
        }
        if (minDuration != null) {
            model.addAttribute("searchMinDuration", minDuration);
        }
        if (maxDuration != null) {
            model.addAttribute("searchMaxDuration", maxDuration);
        }
    }

    public boolean deleteMovie(String id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
