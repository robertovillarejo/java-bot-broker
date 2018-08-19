package io.github.robertovillarejo.bot.dialogflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Fulfillment;
import ai.api.model.Result;
import io.github.robertovillarejo.bot.nlp.NlpService;

@Service
public class DialogflowService implements NlpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DialogflowService.class);

    private AIDataService dataService;

    public DialogflowService(AIDataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public AIResponse talk(AIRequest request) {
        try {
            AIResponse response = dataService.request(request);
            LOGGER.debug(response.toString());
            return response;
        } catch (AIServiceException e) {
            LOGGER.error("Failed to get response");
            AIResponse response = new AIResponse();
            Result result = new Result();
            Fulfillment fulfillment = new Fulfillment();
            fulfillment.setSpeech("¿Sabes? Ahora tengo un dolor de cabeza");
            result.setFulfillment(fulfillment);
            response.setResult(result);
            return response;
        }
    }

}
