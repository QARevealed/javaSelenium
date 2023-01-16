package slack;


import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class Slack {

    private static final boolean SPACE_BETWEEN_FIELD_ROWS = true;

    private Slack() {

    }

    public static void postSlackTextMessage(String message, SlackChannel channel) {
        String requestBody = "{\"text\":\"" + message + "\"}";
        postMessage(requestBody, channel);
    }

    public static void postSlackBlocksMessage(Map<String, String> blocks, SlackChannel channel) {
        var builder = new StringBuilder();

        if (blocks.containsKey("Header")) {
            builder.append("{\n" + "\"type\": \"header\",\n" + "\"text\": {\n" + "\"type\": \"plain_text\",\n" + "\"text\": \"")
                    .append(blocks.get("Header")).append("\",\n").append("\"emoji\": true\n").append("}\n").append("}");
        }

        List<String> messageContentKeys = blocks.keySet().stream().sorted().collect(Collectors.toList());
        messageContentKeys.remove("Header");
        messageContentKeys.remove("URL");
        int numberOfContentKeys = messageContentKeys.size();

        if (SPACE_BETWEEN_FIELD_ROWS) {
            for (var i = 0; i < numberOfContentKeys; i = i + 2) {
                if (builder.length() > 0)
                    builder.append(",\n");
                String key = messageContentKeys.get(i);
                if (i + 1 < numberOfContentKeys) {
                    String nextKey = messageContentKeys.get(i + 1);
                    builder.append("{\n" + "\"type\": \"section\",\n" + "\"fields\": [\n" + "{\n" + "\"type\": \"mrkdwn\",\n" + "\"text\": \"*")
                            .append(key).append(":*\n").append(blocks.get(key)).append("\"\n").append("},\n").append("{\n").append("\"type\": \"mrkdwn\",\n").append("\"text\": \"*").append(nextKey).append(":*\n").append(blocks.get(nextKey)).append("\"\n").append("}\n").append("]\n").append("}");
                } else {
                    builder.append("{\n" + "\"type\": \"section\",\n" + "\"text\": {\n" + "\"type\": \"mrkdwn\",\n" + "\"text\": \"*")
                            .append(key).append(":*\n").append(blocks.get(key)).append("\"\n").append("}\n").append("}");
                }
            }
        } else {
            var counter = 0;
            do {
                if (builder.length() > 0)
                    builder.append(",\n");

                builder.append("{\n\"type\": \"section\",\n\"fields\": [");
                int limit = Math.min(numberOfContentKeys, counter + 10);
                for (int i = counter; i < limit; i++) {
                    if (i > counter)
                        builder.append(",\n");
                    String key = messageContentKeys.get(i);
                    builder.append("{\n" + "\"type\": \"mrkdwn\",\n" + "\"text\": \"*")
                            .append(key).append(":*\n").append(blocks.get(key)).append("\"\n").append("}");
                }
                builder.append("\n]\n}");

                counter = limit;
            } while (numberOfContentKeys > counter);
        }

        if (blocks.containsKey("URL")) {
            if (builder.length() > 0)
                builder.append(",\n");
            builder.append("{\n" + "\"type\": \"section\",\n" + "\"text\": {\n" + "\"type\": \"mrkdwn\",\n" + "\"text\": \"<")
                    .append(blocks.get("URL")).append("|Go to URL>\"\n").append("}\n").append("}");
        }

        String requestBody = "{\"blocks\": [ " + builder + " ] }";
        postMessage(requestBody, channel);
    }

    private static void postMessage(String requestBody, SlackChannel channel) {
        //@formatter:off
        given()
                .body(requestBody)
        .when()
                .post(channel.getWebhook())
        .then()
                .statusCode(HttpStatus.SC_OK);
        //@formatter:on
    }
}
