package com.example.demoapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("word-count")
public class WordCountConfig {
    private boolean caseSensitive;
    private List<String> words_skip;

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public List<String> getWords_skip() {
        return words_skip;
    }

    public void setWords_skip(List<String> words_skip) {
        this.words_skip = words_skip;
    }
}
