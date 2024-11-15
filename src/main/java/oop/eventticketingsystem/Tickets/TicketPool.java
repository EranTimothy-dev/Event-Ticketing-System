package oop.eventticketingsystem.Tickets;

import oop.eventticketingsystem.Configurations.Configuration;
import oop.eventticketingsystem.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;
import static java.util.Collections.synchronizedList;

public class TicketPool implements TicketHandling{


    private static int totalTicketsCreated = 0;
    private static int totalTicketsSold = 0;
    public static List<Ticket> ticketPool = synchronizedList(new ArrayList<Ticket>());
    private final static Configuration configuration = Model.getConfiguration();
    private static int totalTickets = configuration.getNumberOfTickets();
    private static final int maxCapacity = configuration.getMaxTicketCapacity();
    private boolean limitReached = false;
    private boolean capacityReached = false;

    public TicketPool() {
        totalTickets = configuration.getNumberOfTickets();
    }


    @Override
    public void addTickets() {
        if (!limitReached) {
            if (ticketPool.size() < maxCapacity) {
                capacityReached = true;
            }
            if (totalTicketsCreated >= totalTickets) {
                System.out.println("Maximum number of tickets reached.");
                limitReached = true;
                return;
            }
            if (ticketPool.size() >= maxCapacity && !capacityReached) {
                System.out.println("Max Capacity Reached, waiting for tickets to be sold...");
                capacityReached = true;
                return;
            }
            Ticket ticket = new Ticket();
            ticketPool.add(ticket);
            totalTicketsCreated++;
//        System.out.println("Ticket created: " + totalTicketsCreated);
//        System.out.println("Tickets sold: " + totalTicketsSold);
            System.out.printf("""
                    \nTotal Tickets released: %d
                    Total Tickets sold: %d
                    Tickets Remaining: %d\n""", totalTicketsCreated, totalTicketsSold, totalTicketsCreated - totalTicketsSold);

        }
    }

    @Override
    public void removeTickets() {
        if(ticketPool.isEmpty() && totalTicketsCreated == totalTickets){
            System.out.println("All tickets have been sold out!");
            exit(0);
        } else if (ticketPool.isEmpty() && totalTicketsCreated != totalTickets) {
            System.out.println("Waiting for tickets to be released.");
//            try{
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }else{
            totalTicketsSold++;
            ticketPool.remove(ticketPool.size() - 1);
//            System.out.println("Ticket created: " + totalTicketsCreated);
//            System.out.println("Ticket sold: "+ totalTicketsSold);
            System.out.printf("""
                    \nTotal Tickets released: %d
                    Total Tickets sold: %d
                    Tickets Remaining: %d\n""", totalTicketsCreated, totalTicketsSold, totalTicketsCreated - totalTicketsSold);
        }
    }


}
