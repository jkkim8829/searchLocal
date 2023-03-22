package com.project.api.component;

import com.project.api.dto.KakaoVO;
import com.project.api.dto.KakaoVO.documents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KakaoAPIComponentTest {
    @Autowired
    private KakaoAPIComponent kakaoAPIComponent;


    @Test
    public void apiCallTest(){
        String query = "이천오성수족관";

        KakaoVO kakaoVO = new KakaoVO();
        documents documents = new documents();
        documents.setPlace_name("오성수족관");
        documents.setAddress_name("경기 이천시 창전동 460");
        documents.setRoad_address_name("경기 이천시 영창로 250");
        documents.setPhone("031-633-9101");
        kakaoVO.setDocuments(new documents[]{documents});

        KakaoVO kakao = kakaoAPIComponent.localAPI(query).block();

        Assertions.assertEquals(kakaoVO.getDocuments()[0].getPlace_name(), kakao.getDocuments()[0].getPlace_name());
    }
}
