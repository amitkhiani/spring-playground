package com.example.demoapp;

import com.example.demoapp.service.WordCounterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordCounterController.class)
public class WordCounterControllerTest {

    @MockBean
    private WordCounterService wordCounterService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testForWordCountEndPoint() throws Exception {

        when(wordCounterService.wordCounter(anyString())).thenReturn(new HashMap<>());

        this.mockMvc.perform(post("/words/count").contentType(MediaType.TEXT_PLAIN_VALUE).content("to the moon, to the moon"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
