package com.daw.swapp.controller.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.daw.swapp.model.characters.CharacterDetails;
import com.daw.swapp.service.characters.CharacterService;

@RestController
public class CharacterDetailsController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/api/character/{id}")
    public ResponseEntity<CharacterDetails> getCharacterDetails(@PathVariable String id) {
        CharacterDetails charDetails = characterService.getCharacterDetails(id).getProperties();
        return ResponseEntity.ok(charDetails);

    }
}
