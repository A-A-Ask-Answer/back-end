package aa.project.ask.controller;

import aa.project.ask.dto.TranslateResponseDto;
import aa.project.ask.service.TranslateService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "Papago 번역")
public class TranslateController {
    private final TranslateService translateService;


    @PostMapping("/api/translate")
    public TranslateResponseDto.Result naverPapagoTranslate(String source, String target, String text) {
        return translateService.naverPapagoTranslate(source, target, text);
    }

}
