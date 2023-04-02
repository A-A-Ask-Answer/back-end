package aa.project.ask.service;

import aa.project.ask.dto.TranslateResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class TranslateService {

    @Value("${naver.client-id}")
    private String clientId;
    @Value("${naver.client-secret}")
    private String clientSecret;


    public TranslateResponseDto.Result naverPapagoTranslate(String source, String target, String text) {

        WebClient webClient = WebClient.builder()
                .baseUrl("https://openapi.naver.com/v1/papago/n2mt")
                .build();
        TranslateResponseDto response = webClient.post().uri(
                        uriBuilder -> uriBuilder.queryParam("source", source)
                                .queryParam("target", target)
                                .queryParam("text", text)
                                .build()
                )
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .retrieve()
                .bodyToMono(TranslateResponseDto.class).block();
        return response.getMessage().getResult();
    }
}
