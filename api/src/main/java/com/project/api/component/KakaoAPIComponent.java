package com.project.api.component;

import com.project.api.dto.KakaoVO;
import com.project.api.domain.KakaoAPI;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KakaoAPIComponent implements APIComponent {
    //API 관련 값 정의
    private final KakaoAPI kakaoAPI;

    public KakaoAPIComponent(KakaoAPI kakaoAPI) {
        this.kakaoAPI = kakaoAPI;
    }

    public WebClient webClient(){
        return WebClient.builder().baseUrl(kakaoAPI.getBaseUrl()).
                defaultHeader(kakaoAPI.getHeaderName(), kakaoAPI.getHeaderValue()).build();
    }

    public Mono<KakaoVO> localAPI(String query) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", query);
        params.add("size", "5");

        //API호출
        Mono<KakaoVO> mono = webClient().get().
                uri(builder -> builder.path(kakaoAPI.getLocalPath()).queryParams(params).build())
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(KakaoVO.class);
                    } else {
                        return response.bodyToMono(KakaoVO.class);
                    }
                });
        return mono;
    }
}
