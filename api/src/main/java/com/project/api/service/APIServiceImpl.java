package com.project.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.api.component.KakaoAPIComponent;
import com.project.api.component.NaverAPIComponent;
import com.project.api.dto.KakaoVO;
import com.project.api.dto.LocalDTO;
import com.project.api.dto.NaverVO;
import com.project.search.service.SearchService;
import com.project.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class APIServiceImpl implements APIService {
    //API 관련 값 정의
    @Autowired
    private SearchService searchService;
    @Autowired
    private KakaoAPIComponent kakaoAPIComponent;
    @Autowired
    private NaverAPIComponent naverAPIComponent;

    @Override
    public String searchLocal(String query) {
        //병렬처리 WebClient 호출
        Mono<KakaoVO> kakaoMono = kakaoAPIComponent.localAPI(query);
        Mono<NaverVO> naverMono = naverAPIComponent.localAPI(query);

        //처리 완료 후 값 매핑
        KakaoVO kakaoVO = kakaoMono.block();
        NaverVO naverVO = naverMono.block();

        //API에러 리턴 처리
        if(!StringUtils.isEmpty(kakaoVO.getErrorType()) || !StringUtils.isEmpty(naverVO.getErrorCode())) return errorAPI(kakaoVO, naverVO);

        //API merge
        //장소명칭과 도로명 주소 비교를 통한 merge
        LocalDTO local = mergeRespone(kakaoVO, naverVO);

        //검색 키워드 등록
        searchService.saveSearchWord(query);
        return makeJson(local);
    }

    private LocalDTO mergeRespone(KakaoVO kv, NaverVO nv) {
        LocalDTO local = new LocalDTO();
        LocalDTO.place[] places = new LocalDTO.place[]{};
        LinkedHashSet<KakaoVO.documents> kakaoSet = new LinkedHashSet<>();
        LinkedHashSet<NaverVO.items> naverSet = new LinkedHashSet<>();

        //정렬하며 순서대로 넣기위한 LinkedHashSet사용(넣은순서대로 출력)
        for (NaverVO.items item : nv.getItems()) {
            naverSet.add(item);
        }

        loop:
        for (KakaoVO.documents document : kv.getDocuments()) {
            for (NaverVO.items item : nv.getItems()) {
                if (StringUtil.removeTag(document.getPlace_name()).equals(StringUtil.removeTag(item.getTitle()))
                        && StringUtil.subStringSpaceBar(document.getRoad_address_name()).equals(StringUtil.subStringSpaceBar(item.getRoadAddress()))) {
                    places = addPlace(places, setPlace(document));
                    naverSet.remove(item);
                    continue loop;
                }
            }
            kakaoSet.add(document);
        }

        for (KakaoVO.documents document : kakaoSet) {
            places = addPlace(places, setPlace(document));
        }

        for (NaverVO.items item : naverSet) {
            places = addPlace(places, setPlace(item));
        }

        local.setPlaces(places);

        return local;
    }

    private LocalDTO.place setPlace(KakaoVO.documents document) {
        LocalDTO.place place =  new LocalDTO.place();

        place.setPlace_name(document.getPlace_name());
        place.setAddress_name(document.getAddress_name());
        place.setRoad_address_name(document.getRoad_address_name());
        place.setPhone(document.getPhone());

        return place;
    }

    private LocalDTO.place setPlace(NaverVO.items items) {
        LocalDTO.place place =  new LocalDTO.place();

        place.setPlace_name(items.getTitle());
        place.setAddress_name(items.getAddress());
        place.setRoad_address_name(items.getRoadAddress());
        place.setPhone(items.getTelephone());

        return place;
    }

    private LocalDTO.place[] addPlace(LocalDTO.place[] places, LocalDTO.place place) {
        place.setPlace_name(StringUtil.removeTag(place.getPlace_name()));
        if(places.length == 0){
            return new LocalDTO.place[]{place};
        }
        LocalDTO.place[] newPlaces =  new LocalDTO.place[places.length+1];

        for (int i = 0; i < places.length; i++) {
            newPlaces[i] = places[i];
        }

        newPlaces[places.length] = place;

        return newPlaces;
    }

    private String makeJson(LocalDTO local) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(local);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private String errorAPI(KakaoVO kakaoVO, NaverVO naverVO){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            if(StringUtils.isEmpty(kakaoVO.getErrorType())) {
                json = mapper.writeValueAsString(kakaoVO);
            } else {
                json = mapper.writeValueAsString(kakaoVO);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}