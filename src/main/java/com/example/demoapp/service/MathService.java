package com.example.demoapp.service;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MathService {

    public static final String OPERATION_QUERY_STR = "operation";
    public static final String X_QUERY_STR = "x";
    public static final String Y_QUERY_STR = "y";
    public static final String N_QUERY_STR = "n";
    public static final String ADD_OPERATION = "add";
    public static final String MULTIPLY_OPERATION = "multiply";
    public static final String SUBTRACT_OPERATION = "subtract";
    public static final String DIVISION_OPERATION = "divide";
    public static final String CIRCLE_TYPE = "circle";
    public static final String RECTANGLE_TYPE = "rectangle";
    public static final String INVALID_RESPONSE = "Invalid";

    public String getResultForOperation(Map<String, String> queryString) {
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

    public String getSumForOperation(MultiValueMap<String, String> valuesOfN) {
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

    public String getVolumeForOperation(String length, String width, String height) {
        try {
            StringBuilder response = new StringBuilder();
            int result = Integer.parseInt(length) * Integer.parseInt(width) * Integer.parseInt(height);
            response.append("The volume of a ").append(length).append("x").append(width).append("x").append(height)
                    .append(" rectangle is ").append(result);
            return response.toString();
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            return "Invalid number in path variable";
        }
    }

    public String getAreaForOperation(Map<String, String> requestBody) {
        final String type = requestBody.get("type") == null ? INVALID_RESPONSE : requestBody.get("type");
        System.out.println(type);
        double result;
        StringBuilder response = new StringBuilder("Area of a ");
        if(type.equalsIgnoreCase(CIRCLE_TYPE)) {
            double radius = requestBody.get("radius") == null ? -1 : Integer.parseInt(requestBody.get("radius"));
            if(radius == -1) {
                return INVALID_RESPONSE;
            }
            result = Math.PI * radius * radius;
            response.append(type).append(" with a radius of ").append(radius).append(" is ").append(result);
        } else if(type.equalsIgnoreCase(RECTANGLE_TYPE)) {
            double width = requestBody.get("width") == null ? -1 : Integer.parseInt(requestBody.get("width"));
            double height = requestBody.get("height") == null ? -1 : Integer.parseInt(requestBody.get("height"));
            if(width == -1 || height == -1) {
                return INVALID_RESPONSE;
            }
            result = width * height;
            response.append(width).append("x").append(height).append(" ").append(type).append(" is ").append(result);
        } else if(type.equalsIgnoreCase(INVALID_RESPONSE)) {
            return INVALID_RESPONSE;
        }
        return response.toString();
    }
}
