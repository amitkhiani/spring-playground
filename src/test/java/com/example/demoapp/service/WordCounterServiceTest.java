package com.example.demoapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCounterServiceTest {

    @Autowired
    private WordCounterService wordCounterService;

    @Test
    public void testForCountingNumberOfWordsInASingleAlphabetSentence() {
        assertEquals(2, this.wordCounterService.wordCounter("A B C D E D C B A").get("A").intValue());

    }
    @Test
    public void testForCountingNumberOfWordsInASingleAlphabetSentenceWithCommas() {
        assertEquals(2, this.wordCounterService.wordCounter("A, B, C, D, E, D, C, B, A,").get("B").intValue());

    }

    @Test
    public void testForCountingNumberOfWordsInAGivenSentenceWithCommas() {
        assertEquals(2, this.wordCounterService.wordCounter("to the moon, to the moon").get("moon").intValue());

    }
}
