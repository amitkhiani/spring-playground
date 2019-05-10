package com.example.demoapp;

import com.example.demoapp.model.TicketsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void testForCalculatingFlightsTotalWithStringLiteral() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\":[{\"passenger\": {\"firstName\": \"Some name\",\"lastName\": \"Some other name\"},\"price\": 200},{\"passenger\": {\"firstName\": \"Name B\",\"lastName\": \"Name C\" },\"price\": 150 }]}");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testForCalculatingFlightsTotalWithJsonMapper() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TicketsRequest.Ticket[] passengerArray = new TicketsRequest.Ticket[2];
        // Ticket 1.
        TicketsRequest.Ticket ticket = new TicketsRequest.Ticket();
        TicketsRequest.Ticket.Passenger passenger = new TicketsRequest.Ticket.Passenger();
        passenger.setFirstName("Some name");
        passenger.setLastName("Some other name");
        ticket.setPassenger(passenger);
        ticket.setPrice(200);
        passengerArray[0] = ticket;
        // Ticket 2.
        TicketsRequest.Ticket ticket2 = new TicketsRequest.Ticket();
        TicketsRequest.Ticket.Passenger passenger2 = new TicketsRequest.Ticket.Passenger();
        passenger2.setFirstName("Name B");
        passenger2.setLastName("Name C");
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(150);
        passengerArray[1] = ticket2;
        Map<String, Object> data = new HashMap<String, Object>(){
            {
                put("tickets", passengerArray);
            }
        };
        final String jsonStr = mapper.writeValueAsString(data);
        MockHttpServletRequestBuilder requestBuilder = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr);
        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }
}