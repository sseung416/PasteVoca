package co.kr.dgsw.searchvoca.controller;

import co.kr.dgsw.searchvoca.service.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {
    @Autowired
    private DictionaryServiceImpl service;

    @GetMapping("/dict/{word}")
    public String getSearchResult(@PathVariable("word") String word) {
        return service.getSearchResult(word).toString();
    }
}
