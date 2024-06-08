package com.spring.songs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.songs.model.Song;

// use your model and the type of your id property inside the <>
public interface MongoDbRepository extends MongoRepository<Song, String> {

    public Optional<Song> findByName(String name);
    public List<Song> findAllByName(String name);
}
