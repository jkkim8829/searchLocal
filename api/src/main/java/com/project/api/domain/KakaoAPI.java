package com.project.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("kakao.api")
public class KakaoAPI {
    private String baseUrl;
    private String localPath;
    private String headerName;
    private String headerValue;
}