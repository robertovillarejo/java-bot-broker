package io.github.robertovillarejo.bot.dialogflow;

import java.util.ArrayList;
import java.util.List;

import ai.api.model.AIResponse;
import ai.api.model.ResponseMessage;
import ai.api.model.ResponseMessage.Platform;
import ai.api.model.ResponseMessage.ResponseImage;
import ai.api.model.ResponseMessage.ResponseQuickReply;
import ai.api.model.ResponseMessage.ResponseSpeech;
import me.ramswaroop.jbot.core.facebook.models.Button;
import me.ramswaroop.jbot.core.facebook.models.Message;

public class FacebookConverter {

    private FacebookConverter() {

    }

    public static List<Message> map(AIResponse response) {
        List<Message> messages = new ArrayList<>();

        if (response == null) {
            return messages;
        }

        // Response messages defined in DialogFlow
        List<ResponseMessage> msgs = response.getResult().getFulfillment().getMessages();

        // Map Response Messages to Jbot Messages
        // And add them to list
        msgs.stream().filter(responseMsg -> Platform.FACEBOOK.equals(responseMsg.getPlatform()))
                .forEach(responseMsg -> mapResponses(responseMsg, messages));

        // Sort messages
        // Quick replies are moved to end
        messages.sort((Message m1, Message m2) -> {
            int l1 = 0;
            if (m1.getQuickReplies() != null) {
                l1 = m1.getQuickReplies().length;
            }
            int l2 = 0;
            if (m2.getQuickReplies() != null) {
                l2 = m2.getQuickReplies().length;
            }
            return l1 - l2;
        });

        return messages;
    }

    public static Message toMessage(ResponseImage image) {
        return new Message();
    }

    public static Message toMessage(ResponseQuickReply quickReply) {
        Message msg = new Message();
        List<Button> buttons = new ArrayList<>();

        quickReply.getReplies().forEach(reply -> {
            Button buttonReply = new Button();
            buttonReply.setTitle(reply);
            buttonReply.setPayload(reply);
            buttonReply.setContentType("text");
            buttons.add(buttonReply);
        });
        msg.setQuickReplies(buttons.stream().toArray(Button[]::new));
        msg.setText(quickReply.getTitle());
        return msg;
    }

    public static Message toMessage(ResponseSpeech speech) {
        Message msg = new Message();
        String buttonSpeech = speech.getSpeech().get(0);
        msg.setText(buttonSpeech);
        return msg;
    }

    public static Message toMessage(ResponseMessage responseMessage) {
        if (responseMessage instanceof ResponseSpeech) {
            return toMessage((ResponseSpeech) responseMessage);
        } else if (responseMessage instanceof ResponseQuickReply) {
            return toMessage((ResponseQuickReply) responseMessage);
        }
        return new Message();
    }

    // Map Response Messages to JBot Messages
    public static void mapResponses(ResponseMessage responseMessage, List<Message> messages) {
        messages.add(toMessage(responseMessage));
    }

}
