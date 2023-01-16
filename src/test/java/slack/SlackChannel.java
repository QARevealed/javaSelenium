package slack;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SlackChannel {

    QA_DEMO("tec_pmd_qa_demo", "https://hooks.slack.com/services/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"),
    QARevealed("qacourse","https://hooks.slack.com/services/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")
    ;

    private final String channelName;
    private final String webhook;
}