package io.github.robertovillarejo.bot.fulfillment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.api.model.AIResponse;
import ai.api.model.ResponseMessage;
import ai.api.model.ResponseMessage.ResponseSpeech;
import io.github.robertovillarejo.bot.services.JokeService;

@RestController
@RequestMapping("/api")
public class FulfillmentResource {

    private JokeService jokeService;

    @Autowired
    public FulfillmentResource(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @PostMapping("/fulfillment")
    public AIResponse fulfillment(@RequestBody AIResponse response) {
        System.out.println(response);
        return response;
//        switch (response.getResult().getAction()) {
//        case "chuckNorrisJoke":
//            String joke = jokeService.getJoke();
//            ResponseSpeech speech = new ResponseMessage.ResponseSpeech();
//            speech.setSpeech(joke);
//            response.getResult().getFulfillment().getMessages().add(speech);
//            break;
//        case "action":
//            break;
//        default:
//            break;
//        }
//        return response;
    }

}
