package com.example.demoapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MathServiceTest {

    @Autowired
    private MathService mathService;

    @Test
    public void testForVolumeOperationWith345() {
        assertEquals("Checking for volume.", "The volume of a 3x4x5 rectangle is 60", mathService.getVolumeForOperation("3", "4", "5"));
    }

    @Test
    public void testForVolumeOperationWith678() {
        assertEquals("Checking for volume.", "The volume of a 6x7x8 rectangle is 336", mathService.getVolumeForOperation("6", "7", "8"));
    }

    @Test
    public void testForVolumeOperationWithZeroLength() {
        assertEquals("Checking for volume.", "The volume of a 0x4x5 rectangle is 0", mathService.getVolumeForOperation("0", "4", "5"));
    }

    @Test
    public void testForVolumeOperationWithInvalidLength() {
        assertEquals("Checking for volume.", "Invalid number in path variable", mathService.getVolumeForOperation("invalid", "7", "8"));
    }

    @Test
    public void testForAreaOperationForCircleWithRadius() {
        Map<String,String> body = new HashMap<>();
        body.put("type", "circle");
        body.put("radius", "4");
        assertEquals("Checking for area of a circle with radius.", "Area of a circle with a radius of 4.0 is 50.26548245743669", mathService.getAreaForOperation(body));
    }

    @Test
    public void testForAreaOperationForCircleWithoutRadius() {
        Map<String,String> body = new HashMap<>();
        body.put("type", "circle");
        assertEquals("Checking for area of a circle without radius.", "Invalid", mathService.getAreaForOperation(body));
    }

    @Test
    public void testForAreaOperationForRectangleWithWidthAndHeight() {
        Map<String,String> body = new HashMap<>();
        body.put("type", "rectangle");
        body.put("width", "4");
        body.put("height", "7");
        assertEquals("Checking for area of a rectangle with width and height.", "Area of a 4.0x7.0 rectangle is 28.0", mathService.getAreaForOperation(body));
    }

    @Test
    public void testForAreaOperationForRectangleWithoutWidth() {
        Map<String,String> body = new HashMap<>();
        body.put("type", "rectangle");
        body.put("radius", "4");
        assertEquals("Checking for area of a rectangle without width and height.", "Invalid", mathService.getAreaForOperation(body));
    }
}
