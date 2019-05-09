package com.example.demoapp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FlightResponse {
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm")
    @JsonProperty("Departs")
    private Date departs;
    @JsonProperty("Tickets")
    private List<Ticket> tickets = Collections.emptyList();

    static class Ticket {
        @JsonProperty("Passenger")
        private Passenger passenger;
        @JsonProperty("Price")
        private int price;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        static class Passenger {
            @JsonProperty("FirstName")
            private String firstName;
            @JsonProperty("LastName")
            private String lastName;

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }
        }

        public Passenger getPassenger() {
            return passenger;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}