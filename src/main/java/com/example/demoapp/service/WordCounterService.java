package com.example.demoapp.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordCounterService {

    public Map<String, Integer> wordCounter(String sentence) {
        Map<String, Integer> wordCounterMap = new HashMap<>();
        String[] splitArray = sentence.split(" ");
        for(String str : splitArray) {
            str = str.replaceAll(",","");
            int currentCount = wordCounterMap.get(str) == null ? 0 : wordCounterMap.get(str);
            if(currentCount > 0) {
                wordCounterMap.put(str, currentCount + 1);
            } else {
                wordCounterMap.put(str, 1);
            }
        }
        return wordCounterMap;
    }
}
