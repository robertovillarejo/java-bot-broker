package io.github.robertovillarejo.bot.fulfillment;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ai.api.model.Fulfillment;
import ai.api.model.ResponseMessage;
import ai.api.model.ResponseMessage.ResponseSpeech;
import io.github.robertovillarejo.bot.dialogflow.AIWebhookRequest;
import io.github.robertovillarejo.bot.services.JokeService;

@RestController
@RequestMapping("/api")
public class FulfillmentResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FulfillmentResource.class);

    private JokeService jokeService;

    @Autowired
    public FulfillmentResource(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @PostMapping("/fulfillment")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Fulfillment fulfillment(@RequestBody AIWebhookRequest response) {
        Fulfillment fulfillment = new Fulfillment();
        List<ResponseMessage> messages = new ArrayList<>();
        switch (response.getResult().getAction()) {
        case "chuckNorrisJoke":
            LOGGER.debug("REST request to get a Chuck Norris Joke");
            String joke = jokeService.getJoke();
            ResponseSpeech speech = new ResponseMessage.ResponseSpeech();
            speech.setSpeech(joke);
            messages.add(speech);
            // fulfillment.setMessages(messages);
            fulfillment.setSpeech(joke);
            break;
        case "action":
            break;
        default:
            break;
        }
        return fulfillment;
    }

}
