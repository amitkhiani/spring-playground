package com.example.demoapp;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class CalculatorController {

    public static final String OPERATION_QUERY_STR = "operation";
    public static final String X_QUERY_STR = "x";
    public static final String Y_QUERY_STR = "y";
    public static final String N_QUERY_STR = "n";
    public static final String ADD_OPERATION = "add";
    public static final String MULTIPLY_OPERATION = "multiply";
    public static final String SUBTRACT_OPERATION = "subtract";
    public static final String DIVISION_OPERATION = "divide";

    @GetMapping("/math/calculate")
    public String mathCalculator(@RequestParam Map<String,String> queryString) {
        int valueOfX = Integer.parseInt(queryString.get(X_QUERY_STR));
        int valueOfY = Integer.parseInt(queryString.get(Y_QUERY_STR));
        int result;
        StringBuilder responseStr = new StringBuilder();
        if(queryString.get(OPERATION_QUERY_STR) == null || (queryString.get(OPERATION_QUERY_STR).equalsIgnoreCase(ADD_OPERATION))) {
            result = valueOfX + valueOfY;
            responseStr.append(valueOfX).append(" + ").append(valueOfY).append(" = ").append(result);
            return responseStr.toString();
        } else if (queryString.get(OPERATION_QUERY_STR).equalsIgnoreCase(MULTIPLY_OPERATION)) {
            result = valueOfX * valueOfY;
            responseStr.append(valueOfX).append(" * ").append(valueOfY).append(" = ").append(result);
        } else if (queryString.get(OPERATION_QUERY_STR).equalsIgnoreCase(SUBTRACT_OPERATION)) {
            result = valueOfX - valueOfY;
            responseStr.append(valueOfX).append(" - ").append(valueOfY).append(" = ").append(result);
        } else if (queryString.get(OPERATION_QUERY_STR).equalsIgnoreCase(DIVISION_OPERATION)) {
            result = valueOfX / valueOfY;
            responseStr.append(valueOfX).append(" / ").append(valueOfY).append(" = ").append(result);
        }
        return responseStr.toString();
    }

    @PostMapping("/math/sum")
    public String mathCalculatorWithOnlyAddition(@RequestParam MultiValueMap<String, String> valuesOfN) {
        int result;
        AtomicInteger counter = new AtomicInteger(0);
        StringBuilder responseStr = new StringBuilder();
        List<String> valuesList = valuesOfN.get(N_QUERY_STR);
        if(valuesList == null || valuesList.size() == 0) {
            return "Invalid!";
        }
        final int totalElements = valuesList.size();
        result = valuesList.stream().mapToInt(z -> Integer.parseInt(z)).sum();
        valuesList.forEach(x -> {
            counter.getAndIncrement();
            responseStr.append(x);
            if(counter.get() < totalElements) {
                responseStr.append(" + ");
            }
        });
        responseStr.append(" = ").append(result);
        return responseStr.toString();
    }
}
