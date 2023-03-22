package com.project.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("naver.api")
public class NaverAPI {
    private String baseUrl;
    private String localPath;
    private String headerId;
    private String headerSecret;
    private String headerIdValue;
    private String headerSecretValue;
}
