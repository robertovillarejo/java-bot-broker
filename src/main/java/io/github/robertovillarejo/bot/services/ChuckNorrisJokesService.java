package io.github.robertovillarejo.bot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChuckNorrisJokesService implements JokeService {

    private static final String CHUCK_NORRIS_API_URL = "https://api.chucknorris.io/jokes/random";

    private RestTemplate restTemplate;

    @Autowired
    public ChuckNorrisJokesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getJoke() {
        return "Por qué cruzó la gallina?";
    }

}
