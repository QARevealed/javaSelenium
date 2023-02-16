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
