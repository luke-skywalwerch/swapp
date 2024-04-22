package com.daw.swapp.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.daw.swapp.model.CharacterDetails;
import com.daw.swapp.model.CharacterDetailsResponse;
import com.daw.swapp.model.CharacterDetailsResponse.CharacterDetailsResult;
import com.daw.swapp.service.CharacterService;

@ExtendWith(MockitoExtension.class)
public class CharacterDetailsControllerTest {

    @Mock
    private CharacterService characterService;

    @InjectMocks
    private CharacterDetailsController characterDetailsController;

    @Test
    void testGetCharacterDetails() {
        String characterId = "1";
        CharacterDetailsResponse mockResponse = new CharacterDetailsResponse();
        CharacterDetailsResult characterDetailsResult = new CharacterDetailsResult();
        CharacterDetails characterDetails = new CharacterDetails();
        characterDetailsResult.setProperties(characterDetails);
        mockResponse.setResult(characterDetailsResult);

        when(characterService.getCharacterDetails(characterId)).thenReturn(mockResponse);

        CharacterDetails actualDetails = characterDetailsController.getCharacterDetails(characterId);
        System.out.println(actualDetails);

        assertEquals(characterDetails, actualDetails, "The returned character details should be the same as expected.");
        verify(characterService).getCharacterDetails(characterId);
    }
}
