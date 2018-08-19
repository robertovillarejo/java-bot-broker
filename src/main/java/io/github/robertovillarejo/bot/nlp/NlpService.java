package io.github.robertovillarejo.bot.nlp;

import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public interface NlpService {

    public AIResponse talk(AIRequest request);

}
