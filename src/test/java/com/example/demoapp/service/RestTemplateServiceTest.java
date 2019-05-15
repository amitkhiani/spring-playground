package com.example.demoapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateServiceTest {

    @Autowired
    private RestTemplateService restTemplateService;

    @Test
    public void testForMoviesSearchWithMockServer() {
        MockRestServiceServer serviceServer = MockRestServiceServer.createServer(restTemplateService.getRestTemplate());
        serviceServer.expect(requestTo(restTemplateService.OMDB_API_URL + "?apikey=" + restTemplateService.API_KEY + "&s=harry"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("Something!", MediaType.APPLICATION_JSON));
        restTemplateService.callOmdbApiHttpRequest("harry");
        serviceServer.verify();
    }
}
