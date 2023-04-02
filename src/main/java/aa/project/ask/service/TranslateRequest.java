package aa.project.ask.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TranslateRequest {
    private String source;
    private String target;
    private String text;
}
