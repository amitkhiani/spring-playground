package com.example.demoapp;

import com.example.demoapp.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CalculatorController {

    @Autowired
    private MathService mathService;

    @GetMapping("/math/calculate")
    public String mathCalculator(@RequestParam Map<String,String> queryString) {
        return mathService.getResultForOperation(queryString);
    }

    @PostMapping("/math/sum")
    public String mathCalculatorWithOnlyAddition(@RequestParam MultiValueMap<String, String> valuesOfN) {
        return mathService.getSumForOperation(valuesOfN);
    }
}
