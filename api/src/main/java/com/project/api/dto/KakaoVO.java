package com.project.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class KakaoVO {
    private String errorType;
    private String message;
    private documents[] documents;
    @Getter
    @Setter
    public static class documents {
        private String place_name;
        private String road_address_name;
        private String address_name;
        private String phone;
    }
}
