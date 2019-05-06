package com.example.demoapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
