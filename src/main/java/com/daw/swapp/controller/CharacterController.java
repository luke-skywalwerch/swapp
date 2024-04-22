package com.daw.swapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.daw.swapp.model.CharacterResponse;
import com.daw.swapp.service.CharacterService;

@Controller
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/")
    public String showCharacters(Model model) {
        CharacterResponse characters = characterService.listCharacters();
        model.addAttribute("characters", characters.getResults()); 
        return "characters";
    }
}