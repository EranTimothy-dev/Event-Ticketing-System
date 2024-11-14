package oop.eventticketingsystem;

import oop.eventticketingsystem.Configurations.Configuration;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class TicketPool implements TicketHandling{


    private int totalTickets;
    private static int totalTicketsCreated = 0;
    public static List<Ticket> ticketPool = synchronizedList(new ArrayList<Ticket>());

    public TicketPool(Configuration configuration) {
        totalTickets = configuration.getNumberOfTickets();
    }


    @Override
    public void addTickets() {
        if (totalTicketsCreated > totalTickets) {
            System.out.println("Maximum number of tickets reached.");
            return;
        }
        Ticket ticket = new Ticket();
        ticketPool.add(ticket);
        totalTicketsCreated++;
    }

    @Override
    public void removeTickets() {
        if(ticketPool.size() == 0 && totalTicketsCreated == totalTickets){
            System.out.println("All tickets have been sold out!");
            return;
        } else if (ticketPool.size() == 0 & totalTicketsCreated != totalTickets) {
            System.out.println("Waiting for tickets to be released.");
            return;
        }
        ticketPool.removeFirst();

    }


}
