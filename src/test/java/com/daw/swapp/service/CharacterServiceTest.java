package com.daw.swapp.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.daw.swapp.model.CharacterDetails;
import com.daw.swapp.model.CharacterDetailsResponse;
import com.daw.swapp.model.CharacterResponse;
import com.daw.swapp.model.CharacterDetailsResponse.CharacterDetailsResult;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CharacterService characterService;

    @Test
    void testListCharacters() {
        CharacterResponse expectedResponse = new CharacterResponse();
        expectedResponse.setMessage("test");
        expectedResponse.setTotalRecords(234);

        String url = "https://www.swapi.tech/api/people?page=1&limit=10";

        when(restTemplate.getForObject(url, CharacterResponse.class))
                .thenReturn(expectedResponse);

        CharacterResponse actualResponse = characterService.listCharacters();

        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        assertEquals(expectedResponse.getTotalRecords(), actualResponse.getTotalRecords());

        verify(restTemplate).getForObject("https://www.swapi.tech/api/people?page=1&limit=10", CharacterResponse.class);
    }

    @Test
    void testGetCharacterDetails() {
        String characterId = "1";
        CharacterDetailsResponse expectedResponse = new CharacterDetailsResponse();

        String url = "https://www.swapi.tech/api/people/" + characterId;

        when(restTemplate.getForObject(url,
                CharacterDetailsResponse.class))
                .thenReturn(expectedResponse);

        CharacterDetailsResponse actualResponse = characterService.getCharacterDetails(characterId);

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate).getForObject("https://www.swapi.tech/api/people/" + characterId,
                CharacterDetailsResponse.class);
    }
}