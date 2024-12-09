package oop.eventticketingsystem.model.users;

import oop.eventticketingsystem.model.configurations.Configuration;
import oop.eventticketingsystem.model.Model;
import oop.eventticketingsystem.model.tickets.TicketPool;

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
