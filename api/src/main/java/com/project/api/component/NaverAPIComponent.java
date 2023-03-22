package com.project.api.component;

import com.project.api.domain.NaverAPI;
import com.project.api.dto.NaverVO;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NaverAPIComponent implements APIComponent {
    private final NaverAPI naverAPI;

    public NaverAPIComponent(NaverAPI naverAPI) {
        this.naverAPI = naverAPI;
    }

    public WebClient webClient(){
        return WebClient.builder().baseUrl(naverAPI.getBaseUrl()).
                defaultHeaders(headers -> {
                    headers.add(naverAPI.getHeaderId(), naverAPI.getHeaderIdValue());
                    headers.add(naverAPI.getHeaderSecret(), naverAPI.getHeaderSecretValue());
                }).build();
    }

    public Mono<NaverVO> localAPI(String query) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", query);
        params.add("display", "5");

        //API호출
        Mono<NaverVO> mono = webClient().get().
                uri(builder -> builder.path(naverAPI.getLocalPath()).queryParams(params).build())
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(NaverVO.class);
                    } else {
                        return response.bodyToMono(NaverVO.class);
                    }
                });
        return mono;
    }
}
