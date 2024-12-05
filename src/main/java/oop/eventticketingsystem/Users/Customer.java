package oop.eventticketingsystem.Users;

import oop.eventticketingsystem.Configurations.Configuration;
import oop.eventticketingsystem.Model;
import oop.eventticketingsystem.Tickets.TicketPool;
import org.springframework.boot.context.annotation.Configurations;

public class Customer implements Runnable{

    public int customerRetrievalRate;
    private static final Configuration config = Model.getConfiguration();

    public Customer() {
        this.customerRetrievalRate = config.getCustomerRetrievalRate();
    }

    @Override
    public void run(){
        for(int i = 0; i < customerRetrievalRate; i++){
            TicketPool ticketPool = Model.getTicketPool();
            ticketPool.removeTickets();
        }
    }
}
