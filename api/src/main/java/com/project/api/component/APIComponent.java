package com.project.api.component;

import org.springframework.web.reactive.function.client.WebClient;

public interface APIComponent {
    WebClient webClient();
}
