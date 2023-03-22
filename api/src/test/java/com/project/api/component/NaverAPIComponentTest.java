package com.project.api.component;

import com.project.api.dto.NaverVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverAPIComponentTest {
    @Autowired
    private NaverAPIComponent naverAPIComponent;

    @Test
    public void apiCallTest(){
        String query = "이천오성수족관";

        NaverVO naverVO = new NaverVO();
        NaverVO.items item = new NaverVO.items();
        item.setTitle("<b>오성수족관</b>");
        item.setAddress("경기도 이천시 창전동 460");
        item.setRoadAddress("경기도 이천시 영창로 250");
        item.setTelephone("");
        naverVO.setItems(new NaverVO.items[]{item});

        NaverVO naver = naverAPIComponent.localAPI(query).block();

        Assertions.assertEquals(naverVO.getItems()[0].getTitle(), naver.getItems()[0].getTitle());
    }
}
