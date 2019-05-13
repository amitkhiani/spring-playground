package com.example.demoapp.service;

import com.example.demoapp.config.WordCountConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordCounterService {

    @Autowired
    private WordCountConfig wordCountConfig;

    public Map<String, Integer> wordCounter(String sentence) {
        Map<String, Integer> wordCounterMap = new HashMap<>();
        boolean isWordToBeIgnored;
        String[] splitStrArray = sentence.split(" ");
        for(String str : splitStrArray) {
            isWordToBeIgnored = false;
            str = str.replaceAll(",","");
            if(!wordCountConfig.isCaseSensitive()) {
                str = str.toLowerCase();
            }
            for(String strConfig : wordCountConfig.getWords_skip()) {
                if(str.equalsIgnoreCase(strConfig)) {
                    isWordToBeIgnored = true;
                    break;
                }
            }
            if(!isWordToBeIgnored) {
                int currentCount = wordCounterMap.get(str) == null ? 0 : wordCounterMap.get(str);
                if(currentCount > 0) {
                    wordCounterMap.put(str, currentCount + 1);
                } else {
                    wordCounterMap.put(str, 1);
                }
            }
        }
        return wordCounterMap;
    }
}
