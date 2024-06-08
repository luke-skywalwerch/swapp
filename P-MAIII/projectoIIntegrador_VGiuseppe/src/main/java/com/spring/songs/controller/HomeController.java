package com.spring.songs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.songs.model.Song;
import com.spring.songs.repository.MongoDbRepository;

// annotation to define controller
@Controller
public class HomeController {

    // annotation to inject the repository
    @Autowired
    private MongoDbRepository mongoRepo;

    @GetMapping("/songs")
    public String showSongs(Model model) {
        List<Song> songs = mongoRepo.findAll();
        model.addAttribute("songs", songs);
        return "songs";
    }

    @RequestMapping("/new-song")
    public String agregarMusica(@ModelAttribute Song song, Model model) {
        model.addAttribute("song", song);
        if (song.getName() != null && !song.getName().isEmpty()) {
            mongoRepo.save(song);
            return "redirect:/songs";
        }
        return "new-song";
    }

    @RequestMapping("/delete-song")
    public String eliminarMusica(@ModelAttribute Song song, Model model) {
        if (song.getName() != null && !song.getName().isEmpty()) {
            List<Song> opt = mongoRepo.findAllByName(song.getName());
            if (opt.size() >= 1) {
                mongoRepo.delete(opt.get(0));
            }
            return "redirect:/songs";
        }
        return "delete-song";
    }

    @RequestMapping("/update-song")
    public String editarMusica(@ModelAttribute Song song, Model model) {
        if (song.getName() != null && !song.getName().isEmpty()) {
            if (checkAllFields(song)) {
                List<Song> optSong = mongoRepo.findAllByName(song.getName());
                if (optSong.size() >= 1) {
                    Song aux = song;
                    song = optSong.get(0);
                    song.setName(aux.getName());
                    song.setArtists(aux.getArtists());
                    song.setGenre(aux.getGenre());
                    song.setReleaseDate(aux.getReleaseDate());
                    song.setReproductions(aux.getReproductions());
                }
                mongoRepo.save(song);
                return "redirect:/songs";
            }
            List<Song> optSong = mongoRepo.findAllByName(song.getName());
            if (optSong.size() >= 1) {
                model.addAttribute("song", optSong.get(0));
            }
            return "update-song";
        }
        return "update-song-search";
    }

    private boolean checkAllFields(Song song) {
        return song.getName() != null &&
                song.getArtists() != null &&
                song.getGenre() != null &&
                song.getReleaseDate() != null;
    }

}
