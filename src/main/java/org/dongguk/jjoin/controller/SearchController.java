package org.dongguk.jjoin.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.jjoin.dto.response.SearchClubDto;
import org.dongguk.jjoin.dto.response.TagDto;
import org.dongguk.jjoin.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    @GetMapping
    public Map<String, Object> searchClubs(@RequestParam String keyword, @RequestParam List<String> tags, @RequestParam Integer page, @RequestParam Integer size){
        Map<String, Object> result = new HashMap<>();
        result.put("clubs", searchService.searchClubs(keyword, tags, page, size));
        return result;
    }

    // 동아리 검색하기 위해 모든 태그 목록 조회
    @GetMapping("/tags")
    public List<TagDto> readAllTags() {
        return searchService.readAllTags();
    }
}