package com.example.demoapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(JsonController.class)
public class JsonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testForFlightDetails() throws Exception {
        this.mockMvc.perform(get("/flights/flight"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Amit")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is("Khiani")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
    }

    @Test
    public void testForListOfFlights() throws Exception {
        this.mockMvc.perform(get("/flights"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)));
    }
}
