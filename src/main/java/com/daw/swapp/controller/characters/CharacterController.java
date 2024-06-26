package com.daw.swapp.controller.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.swapp.model.characters.CharacterResponse;
import com.daw.swapp.service.characters.CharacterService;

@Controller
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/characters")
    public String showCharacters(@RequestParam(defaultValue = "1") int page, Model model) {
        CharacterResponse characters = characterService.listCharacters(page);
        model.addAttribute("characters", characters.getResults());
        return "characters/home";
    }
}