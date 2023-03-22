package com.project.api.controller;

import com.project.api.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
    @Autowired
    private APIService apiService;

    @GetMapping("/search")
    public String searchLocal(@RequestParam String query) throws Exception {
        return apiService.searchLocal(query);
    }
}