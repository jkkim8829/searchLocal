package com.project.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverVO {
    private items[] items;
    private String errorMessage;
    private String errorCode;
    @Getter
    @Setter
    public static class items {
        private String title;
        private String roadAddress;
        private String address;
        private String telephone;
    }
}
