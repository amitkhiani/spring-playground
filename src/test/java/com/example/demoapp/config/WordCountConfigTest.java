package com.example.demoapp.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "word-count.caseSensitive=false",
        "word-count.words_skip[0]=the",
        "word-count.words_skip[1]=an",
        "word-count.words_skip[2]=a"
})
public class WordCountConfigTest {

    @Autowired
    private WordCountConfig wordCountConfig;

    @Test
    public void testForApplicationPropertiesMatchCorrectly() {
        assertEquals(false, wordCountConfig.isCaseSensitive());
        assertThat(wordCountConfig.getWords_skip(), contains("the", "an", "a"));
    }

}
