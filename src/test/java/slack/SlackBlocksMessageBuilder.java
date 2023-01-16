package slack;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class SlackBlocksMessageBuilder {

    @Getter
    private final Map<String, String> message = new HashMap<>();

    public void setTitle(String messageTitle) {
        message.put("Header", messageTitle);
    }

    public void addMessageContent(String fieldName, String fieldText) {
        message.put(fieldName, fieldText);
    }

    public void setUrl(String url) {
        message.put("URL", url);
    }
}
