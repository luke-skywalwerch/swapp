package com.daw.swapp.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.daw.swapp.model.CharacterResponse;
import com.daw.swapp.service.CharacterService;

@ExtendWith(MockitoExtension.class)
public class CharacterControllerTest {

    @Mock
    private CharacterService characterService;

    @Mock
    private Model model;

    @InjectMocks
    private CharacterController characterController;

    @Test
    void testShowCharacters() {
        CharacterResponse expectedCharacters = new CharacterResponse();
        when(characterService.listCharacters()).thenReturn(expectedCharacters);
        
        String viewName = characterController.showCharacters(model);

        assertEquals("characters", viewName);
        verify(model).addAttribute("characters", expectedCharacters.getResults());
        verify(characterService).listCharacters();
    }
}
