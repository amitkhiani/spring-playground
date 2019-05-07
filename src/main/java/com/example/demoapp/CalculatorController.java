package com.example.demoapp;

import com.example.demoapp.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String mathCalculatorForVolume(@PathVariable String length, @PathVariable String width, @PathVariable String height) {
        return mathService.getVolumeForOperation(length, width, height);
    }

    @PostMapping("/math/area")
    public String mathCalculatorForArea(@RequestParam Map<String, String> requestBody) {
        System.out.println(requestBody);
        return mathService.getAreaForOperation(requestBody);
    }
}
