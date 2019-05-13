package com.example.demoapp;

import com.example.demoapp.service.WordCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WordCounterController {

    private final WordCounterService wordCounterService;

    @Autowired
    public WordCounterController(WordCounterService service) {
        this.wordCounterService = service;
    }

    @PostMapping("/words/count")
    public Map<String, Integer> countTheWordsForASentence(@RequestBody String sentence) {
        return wordCounterService.wordCounter(sentence);
    }
}
