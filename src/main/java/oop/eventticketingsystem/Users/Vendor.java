package oop.eventticketingsystem.Users;

import oop.eventticketingsystem.Configurations.Configuration;
import oop.eventticketingsystem.Model;
import oop.eventticketingsystem.Tickets.TicketPool;

public class Vendor implements Runnable{

    private final int ticketReleaseRate;
    private final Configuration configuration = Model.getConfiguration();
    private final TicketPool ticketPool = Model.getTicketPool();

    public Vendor() {
        ticketReleaseRate = configuration.getTicketReleaseRate();
    }

    @Override
    public void run(){
        for (int i = 0; i < ticketReleaseRate; i++) {
            ticketPool.addTickets();
        }

    }
}
