package io.github.robertovillarejo.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ai.api.AIConfiguration;
import ai.api.AIDataService;

@Configuration
@ConfigurationProperties
public class DialogflowConfig {

    @Bean
    public AIDataService aiDataService(@Value("${apiKey}") String apiKey) {
        return new AIDataService(new AIConfiguration(apiKey));
    }

}
