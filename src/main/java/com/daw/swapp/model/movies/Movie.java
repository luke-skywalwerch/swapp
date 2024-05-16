package com.daw.swapp.model.movies;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private List<String> actors;
    private int duration;
    private Details details;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public static class Details {
        private Director director;
        private int year;

        public Director getDirector() {
            return director;
        }

        public void setDirector(Director director) {
            this.director = director;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class Director {
        private String name;
        private String nationallity;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNationallity() {
            return nationallity;
        }

        public void setNationallity(String nationallity) {
            this.nationallity = nationallity;
        }
    }
}
