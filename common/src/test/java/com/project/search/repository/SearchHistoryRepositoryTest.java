package com.project.search.repository;

import com.project.search.domain.PopularSearch;
import com.project.search.domain.SearchHistory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SearchHistoryRepositoryTest {
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Test
    public void saveTest() {
        SearchHistory searchHistory = SearchHistory.builder()
                .searchWord("test").searchDate(LocalDateTime.now()).build();

        SearchHistory savedHistory = searchHistoryRepository.save(searchHistory);

        Assertions.assertEquals(searchHistory.getSearchWord(), savedHistory.getSearchWord());
    }

    @Test
    public void findTop10OrderBySearchCountDescTest() {
        SearchHistory searchHistory = SearchHistory.builder()
                .searchWord("test").searchDate(LocalDateTime.now()).build();

        SearchHistory savedHistory = searchHistoryRepository.save(searchHistory);
        int date = 3;
        List<PopularSearch> findHistory = searchHistoryRepository.findTop10OrderBySearchCountDesc(setDateTime(date));

        Assertions.assertEquals(savedHistory.getSearchWord(), findHistory.get(0).getSearchWord());
    }

    private LocalDateTime setDateTime(int date) {
        LocalDateTime searchDate = LocalDateTime.now();
        if(date == 0){
            searchDate = LocalDateTime.parse("2020-01-01T00:00:00.000");
        } else {
            searchDate = searchDate.minusDays(date);
        }
        return searchDate;
    }
}
