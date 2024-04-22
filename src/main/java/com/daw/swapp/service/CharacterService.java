package com.daw.swapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.daw.swapp.model.CharacterDetailsResponse;
import com.daw.swapp.model.CharacterResponse;

@Service
public class CharacterService {

    private final RestTemplate restTemplate;

    public CharacterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CharacterResponse listCharacters() {
        String url = "https://www.swapi.tech/api/people?page=1&limit=10";
        return restTemplate.getForObject(url, CharacterResponse.class);
    }

    public CharacterDetailsResponse getCharacterDetails(String id) {
        String url = "https://www.swapi.tech/api/people/" + id;
        return restTemplate.getForObject(url, CharacterDetailsResponse.class);
    }
}