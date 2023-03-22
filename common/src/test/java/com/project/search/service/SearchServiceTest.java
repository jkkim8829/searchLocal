package com.project.search.service;

import com.project.search.domain.SearchHistory;
import com.project.search.repository.SearchHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private SearchHistoryRepository searchHistoryRepository;

    @InjectMocks
    private SearchServiceImpl searchService;

    @Test
    public void testSaveSearch() {
        String word = "test";
        searchService.saveSearchWord(word);
    }

    @Test
    public void testGetPopularSearch() {
        int date = 3;
        searchService.getPopularSearchWord(date);
    }
}
