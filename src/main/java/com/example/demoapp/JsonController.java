package com.example.demoapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class JsonController {

    @GetMapping("/flights/flight")
    public FlightResponse flightInformation() {
        FlightResponse response = new FlightResponse();
        FlightResponse.Ticket.Passenger passenger = new FlightResponse.Ticket.Passenger();
        passenger.setFirstName("Amit");
        passenger.setLastName("Khiani");
        FlightResponse.Ticket ticket = new FlightResponse.Ticket();
        ticket.setPassenger(passenger);
        ticket.setPrice(200);
        List<FlightResponse.Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        response.setDeparts(new Date());
        response.setTickets(tickets);
        return response;
    }

    @GetMapping("/flights")
    public List<FlightResponse> flightsInformationList() {
        // Flight details 1.
        FlightResponse response = new FlightResponse();
        FlightResponse.Ticket.Passenger passenger = new FlightResponse.Ticket.Passenger();
        passenger.setFirstName("Some name");
        passenger.setLastName("Some other name");
        FlightResponse.Ticket ticket = new FlightResponse.Ticket();
        ticket.setPassenger(passenger);
        ticket.setPrice(200);
        List<FlightResponse.Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        response.setDeparts(new Date());
        response.setTickets(tickets);
        // Flight details 2.
        FlightResponse response2 = new FlightResponse();
        FlightResponse.Ticket.Passenger passenger2 = new FlightResponse.Ticket.Passenger();
        passenger2.setFirstName("Some other name");
        passenger2.setLastName(null);
        FlightResponse.Ticket ticket2 = new FlightResponse.Ticket();
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(400);
        List<FlightResponse.Ticket> tickets2 = new ArrayList<>();
        tickets2.add(ticket2);
        response2.setDeparts(new Date());
        response2.setTickets(tickets2);
        return Arrays.asList(response, response2);
    }
}
