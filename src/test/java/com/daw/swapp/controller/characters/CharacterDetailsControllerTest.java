package com.daw.swapp.controller.characters;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.daw.swapp.model.characters.CharacterDetails;
import com.daw.swapp.model.characters.CharacterDetailsResponse;
import com.daw.swapp.model.characters.CharacterDetailsResponse.CharacterDetailsResult;
import com.daw.swapp.service.characters.CharacterService;

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

        ResponseEntity<CharacterDetails> detailsResponse = characterDetailsController.getCharacterDetails(characterId);
        CharacterDetails actualDetails = detailsResponse.getBody();

        assertNotNull(detailsResponse);
        assertEquals(HttpStatus.OK, detailsResponse.getStatusCode());
        assertNotNull(actualDetails, "The body of the response should not be null.");
        assertEquals(characterDetails, actualDetails, "The returned character details should be the same as expected.");
        verify(characterService).getCharacterDetails(characterId);
    }
}
