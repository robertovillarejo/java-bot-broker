package io.github.robertovillarejo.bot.dialogflow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import io.github.robertovillarejo.bot.config.DialogflowConfig;

@SpringBootTest
@Import(value = { DialogflowConfig.class })
@RunWith(SpringRunner.class)
public class DialogflowTest {

    @Autowired
    AIDataService dataService;

    @Test
    public void testDialogflow() throws AIServiceException {
         AIResponse response = dataService.request(new AIRequest("hola"));
        System.out.println(response);
    }

}
