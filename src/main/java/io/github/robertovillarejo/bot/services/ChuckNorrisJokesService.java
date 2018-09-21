package io.github.robertovillarejo.bot.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChuckNorrisJokesService implements JokeService {

    private static final String CHUCK_NORRIS_API_URL = "https://api.chucknorris.io/jokes/random";

    private RestTemplate restTemplate;

    public ChuckNorrisJokesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getJoke() {
        return restTemplate.getForObject(CHUCK_NORRIS_API_URL, String.class);
    }

}
