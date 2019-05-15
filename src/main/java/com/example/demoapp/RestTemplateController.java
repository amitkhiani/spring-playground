package com.example.demoapp;

import com.example.demoapp.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTemplateController {

    private final RestTemplateService restTemplateService;

    public RestTemplateController(final RestTemplateService service) {
        this.restTemplateService = service;
    }

    @GetMapping("/movies")
    public String searchForMovies(@RequestParam String q) {
        System.out.println("Query::" + q);
        return this.restTemplateService.callOmdbApiHttpRequest(q);
    }
}
