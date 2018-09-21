package io.github.robertovillarejo.bot.dialogflow;

import java.util.ArrayList;
import java.util.List;

import ai.api.model.AIResponse;
import ai.api.model.ResponseMessage;
import ai.api.model.ResponseMessage.Platform;
import ai.api.model.ResponseMessage.ResponseQuickReply;
import ai.api.model.ResponseMessage.ResponseSpeech;
import me.ramswaroop.jbot.core.facebook.models.Button;
import me.ramswaroop.jbot.core.facebook.models.Element;
import me.ramswaroop.jbot.core.facebook.models.Message;

public class FacebookConverter {

    public String toText(AIResponse response) {
        return null;
    }

    public Element toImage(AIResponse response) {
        return null;
    }

    public Button[] toQuickReplies() {
        return null;
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

    // Map Response Messages to JBot Messages
    public static void mapResponses(ResponseMessage responseMessage, List<Message> messages) {
        if (responseMessage instanceof ResponseSpeech) {
            Message msg = new Message();
            String buttonSpeech = ((ResponseSpeech) responseMessage).getSpeech().get(0);
            msg.setText(buttonSpeech);
            messages.add(msg);
        } else if (responseMessage instanceof ResponseQuickReply) {
            Message msg = new Message();
            List<Button> buttons = new ArrayList<>();

            ResponseQuickReply quickReply = (ResponseQuickReply) responseMessage;

            quickReply.getReplies().forEach(reply -> {
                Button buttonReply = new Button();
                buttonReply.setTitle(reply);
                buttonReply.setPayload(reply);
                buttonReply.setContentType("text");
                buttons.add(buttonReply);
            });
            msg.setQuickReplies(buttons.stream().toArray(Button[]::new));
            msg.setText(((ResponseQuickReply) responseMessage).getTitle());
            int indexToAdd;
            if (messages.isEmpty()) {
                indexToAdd = 0;
            } else {
                indexToAdd = messages.size() - 1;
            }
            messages.add(indexToAdd, msg);
        }
    }

}
