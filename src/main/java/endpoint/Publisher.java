package endpoint;

import javax.xml.ws.Endpoint;

import ws.TicketBooking;

public class Publisher {

    public static void main(String[] args) {

        Endpoint.publish("http://localhost:9999/ws/TicketBooking", new TicketBooking());

        System.out.println("Service is published!");
    }
}
