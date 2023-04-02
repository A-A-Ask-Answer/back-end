package aa.project.ask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TranslateResponse {
    private String srcLangType;
    private String tarLangType;
    private String translatedText;
}
