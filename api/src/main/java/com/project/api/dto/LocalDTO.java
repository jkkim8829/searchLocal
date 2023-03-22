package com.project.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalDTO {
    private LocalDTO.place[] places;
    @Getter
    @Setter
    public static class place {
        private String place_name;
        private String road_address_name;
        private String address_name;
        private String phone;
    }
}
