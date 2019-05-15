package com.example.demoapp;

import com.example.demoapp.service.RestTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestTemplateController.class)
public class RestTemplateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplateService restTemplateService;

    @Test
    public void testForMoviesSearchWithOmdbApi() throws Exception {
        when(restTemplateService.callOmdbApiHttpRequest(anyString())).thenReturn("something");

        this.mockMvc.perform(get("/movies?q=harry"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
