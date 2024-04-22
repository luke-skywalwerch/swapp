package com.daw.swapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.daw.swapp.model.CharacterDetails;
import com.daw.swapp.service.CharacterService;

@RestController
public class CharacterDetailsController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/api/character/{id}")
    public CharacterDetails getCharacterDetails(@PathVariable String id) {
        return characterService.getCharacterDetails(id).getProperties();
    }
}
