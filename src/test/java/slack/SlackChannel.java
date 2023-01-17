package slack;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SlackChannel 
{
QARevealed("qacourse","https://hooks.slack.com/services/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")
    ;

    private final String channelName;
    private final String webhook;
}
