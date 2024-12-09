package oop.eventticketingsystem.model.users;

import oop.eventticketingsystem.model.configurations.Configuration;
import oop.eventticketingsystem.model.Model;
import oop.eventticketingsystem.model.tickets.TicketPool;

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
