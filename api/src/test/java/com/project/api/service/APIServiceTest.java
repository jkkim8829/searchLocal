package com.project.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class APIServiceTest {
    @Autowired
    private APIService apiService;

    @Test
    public void testApiCall() throws Exception {
        //given
        String query = "test";
        String repones = apiService.searchLocal(query);

        String value = "{\"places\":[{\"place_name\":\"test\",\"road_address_name\":\"경기 고양시 덕양구 은빛로 12\",\"address_name\":\"경기 고양시 덕양구 화정동 936\",\"phone\":\"010-3090-8592\"},{\"place_name\":\"LG헬로비전 TEST Lab 전기차충전소\",\"road_address_name\":\"강원 춘천시 동면 소양강로 76\",\"address_name\":\"강원 춘천시 동면 장학리 791-151\",\"phone\":\"1855-1078\"},{\"place_name\":\"테스트원 본사\",\"road_address_name\":\"경기 시흥시 엠티브이28로58번길 3\",\"address_name\":\"경기 시흥시 정왕동 2596\",\"phone\":\"02-899-2233\"},{\"place_name\":\"에이비에이테스트\",\"road_address_name\":\"경기 화성시 비봉면 화성로1616번길 87\",\"address_name\":\"경기 화성시 비봉면 양노리 669-6\",\"phone\":\"\"},{\"place_name\":\"도마베이커리앤테스트키친 양재점\",\"road_address_name\":\"서울 서초구 강남대로23길 12\",\"address_name\":\"서울 서초구 양재동 110-10\",\"phone\":\"02-575-2018\"},{\"place_name\":\"피어슨프로페셔널센터\",\"road_address_name\":\"서울특별시 중구 무교로 21 더익스체인지서울\",\"address_name\":\"서울특별시 중구 무교동 45\",\"phone\":\"\"},{\"place_name\":\"인터텍킴스코 본사\",\"road_address_name\":\"서울특별시 중구 서소문로 115 9층\",\"address_name\":\"서울특별시 중구 서소문동 47-2 9층\",\"phone\":\"\"},{\"place_name\":\"OPIc 시청센터\",\"road_address_name\":\"서울특별시 중구 서소문로 89 순화빌딩\",\"address_name\":\"서울특별시 중구 순화동 5-2\",\"phone\":\"\"},{\"place_name\":\"(사)한국천식알레르기협회\",\"road_address_name\":\"서울특별시 종로구 새문안로 92 광화문오피시아 1701호\",\"address_name\":\"서울특별시 종로구 신문로1가 163 광화문오피시아 1701호\",\"phone\":\"\"},{\"place_name\":\"국제제과디저트협회\",\"road_address_name\":\"서울특별시 종로구 삼일대로 395 종로빌딩 7층\",\"address_name\":\"서울특별시 종로구 종로2가 71-2 종로빌딩 7층\",\"phone\":\"\"}]}";

        Assertions.assertEquals(value, repones);
    }

    @Test
    public void testApiCallError() throws Exception {
        //given
        String query = "";
        String repones = apiService.searchLocal(query);

        String error = "{\"errorType\":\"MissingParameter\",\"message\":\"query parameter required\",\"documents\":null}";

        Assertions.assertEquals(error, repones);
    }
}
