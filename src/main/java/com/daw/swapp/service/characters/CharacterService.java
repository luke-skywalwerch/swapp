package com.daw.swapp.service.characters;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.daw.swapp.model.characters.CharacterDetailsResponse;
import com.daw.swapp.model.characters.CharacterResponse;

@Service
public class CharacterService {

    private final RestTemplate restTemplate;

    public CharacterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CharacterResponse listCharacters(int pageNumber) {
        String url = String.format("https://www.swapi.tech/api/people?page=%s&limit=10", pageNumber);
        return restTemplate.getForObject(url, CharacterResponse.class);
    }

    public CharacterDetailsResponse getCharacterDetails(String id) {
        String url = "https://www.swapi.tech/api/people/" + id;
        return restTemplate.getForObject(url, CharacterDetailsResponse.class);
    }
}