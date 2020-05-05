package Impl;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import dataAccess.ITicket;
import error.ReservationNotFoundException;
import model.Flight;
import model.Passenger;
import DTO.PassengerDTO;
import model.Ticket;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@WebService(endpointInterface = "dataAccess.ITicket")
public class TicketImpl implements ITicket {
    @Override
    public Ticket checkReservation(Integer number_of_reservation, List<Ticket> ticketList) {
        List<Ticket> tickets = ticketList
                .stream()
                .filter(t -> t.code.equals(number_of_reservation))
                .collect(Collectors
                        .toList());
        if(tickets.isEmpty()){
            throw new ReservationNotFoundException("Reservation not found");
        }
        else{
            return tickets.get(0);
        }
    }

    @Override
    public List<Ticket> bookFlight(PassengerDTO passengerDTO, Flight flight, List<Ticket> ticketList) {
            int ticketCode = generateTicketCode(ticketList);
            Ticket ticket = new Ticket(flight, passengerDTO, ticketCode);
            ticketList.add(ticket);
        return ticketList;
    }

    @Override
    public DataHandler printTicketToPdf(Ticket ticket) {
        PdfWriter writer = null;
        Integer ticketCode = ticket.getCode();
        String fileName = new String("Ticket " + ticketCode +".pdf");
        try {
            writer = new PdfWriter(fileName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.add(new Paragraph().add("Origin: " + ticket.getFlight().getFrom_AirPort().getCountry() + ", " + ticket.getFlight().getFrom_AirPort().getCity()));
            document.add(new Paragraph().add("Departure airport: " + ticket.getFlight().getFrom_AirPort().getName() + ", " + ticket.getFlight().getFrom_AirPort().getStreet() + " " + ticket.getFlight().getFrom_AirPort().getNumer_of_building() + "\n\n"));
            document.add(new Paragraph().add("Destination: " + ticket.getFlight().getTo_AirPort().getCountry() + ", " + ticket.getFlight().getTo_AirPort().getCity()));
            document.add(new Paragraph().add("Destination airport: " + ticket.getFlight().getTo_AirPort().getName() + ", " + ticket.getFlight().getTo_AirPort().getStreet() + " " + ticket.getFlight().getTo_AirPort().getNumer_of_building() + "\n\n"));
            document.add(new Paragraph().add("Flight date: " + ticket.getFlight().getFlightDate() + "\n\n\n"));
            document.add(new Paragraph().add("Ticket code: " + ticket.getCode()));

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileDataSource dataSource = new FileDataSource(fileName);
        return new DataHandler(dataSource);
    }

    @Override
    public List<Ticket> getAllTicket(List<Ticket> ticketList) {
        return ticketList;
    }

    @Override
    public List<Ticket> generateTickets(List<Flight> flightList, List<Passenger> passengerList) {
        List<Ticket> tickets = new ArrayList<Ticket>();
        PassengerDTO passengerDTO = new PassengerDTO(passengerList.get(0).getName(), passengerList.get(0).getSurname(), passengerList.get(0).getEmail());
        tickets.add(new Ticket(flightList.get(0), passengerDTO, 33));
        return tickets;
    }

    @Override
    public int generateTicketCode(List<Ticket> ticketList) {
        int code;
        code = new Random().nextInt(1000 + 1) + 10000;
        List<Ticket> tempListTickets;
        do {
            code = new Random().nextInt(1000 + 1) + 10000;
            int finalCode = code;
            tempListTickets = ticketList
                    .stream()
                    .filter(c -> c.equals(finalCode))
                    .collect(Collectors
                            .toList());
        }while(!tempListTickets.isEmpty());
        return code;
    }

}
