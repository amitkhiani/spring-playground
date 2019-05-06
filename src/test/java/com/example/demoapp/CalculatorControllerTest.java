package com.example.demoapp;

import com.example.demoapp.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MathService mathService;

    @Test
    public void testForAddOperationForCalculator() throws Exception {
        when(mathService.getResultForOperation(any())).thenReturn("4 + 6 = 10");

        this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testForAddOperationForCalculatorWhenOperationQueryStringIsAbsent() throws Exception {
        when(mathService.getResultForOperation(any())).thenReturn("4 + 6 = 10");

        this.mvc.perform(get("/math/calculate?x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testForMultiplyOperationForCalculator() throws Exception {
        when(mathService.getResultForOperation(any())).thenReturn("4 * 6 = 24");

        this.mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }

    @Test
    public void testForSubtractOperationForCalculator() throws Exception {
        when(mathService.getResultForOperation(any())).thenReturn("4 - 6 = -2");

        this.mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }

    @Test
    public void testForDivisionOperationForCalculator() throws Exception {
        when(mathService.getResultForOperation(any())).thenReturn("30 / 5 = 6");

        this.mvc.perform(get("/math/calculate?operation=divide&x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));
    }

    @Test
    public void testForSumOperationForCalculatorWithTwoValues() throws Exception {
        when(mathService.getSumForOperation(any())).thenReturn("4 + 5 = 9");

        this.mvc.perform(post("/math/sum?n=4&n=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 = 9"));
    }

    @Test
    public void testForSumOperationForCalculatorWithThreeValues() throws Exception {
        when(mathService.getSumForOperation(any())).thenReturn("4 + 5 + 6 = 15");

        this.mvc.perform(post("/math/sum?n=4&n=5&n=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testForSumOperationForCalculatorWithFourValues() throws Exception {
        when(mathService.getSumForOperation(any())).thenReturn("4 + 5 + 6 + 7 = 22");

        this.mvc.perform(post("/math/sum?n=4&n=5&n=6&n=7"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 + 7 = 22"));
    }

    @Test
    public void testForVolumeWithThreePathVariablesUsingGet() throws Exception {
        when(mathService.getVolumeForOperation(any(),any(),any())).thenReturn("The volume of 3x4x5 rectangle is 60");

        this.mvc.perform(get("/math/volume/3/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of 3x4x5 rectangle is 60"));
    }

    @Test
    public void testForVolumeWithThreePathVariablesUsingPost() throws Exception {
        when(mathService.getVolumeForOperation(any(),any(),any())).thenReturn("The volume of 3x4x5 rectangle is 60");

        this.mvc.perform(post("/math/volume/3/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of 3x4x5 rectangle is 60"));
    }

    @Test
    public void testForVolumeWithThreePathVariablesUsingPatch() throws Exception {
        when(mathService.getVolumeForOperation(any(),any(),any())).thenReturn("The volume of 3x4x5 rectangle is 60");

        this.mvc.perform(patch("/math/volume/3/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of 3x4x5 rectangle is 60"));
    }
}
