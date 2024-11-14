package oop.eventticketingsystem;

import oop.eventticketingsystem.Configurations.Configuration;
import oop.eventticketingsystem.Tickets.TicketPool;

public class Model {

    private static Configuration config;
    private static TicketPool ticketPool;

    public static synchronized Configuration getConfiguration(){
        if(config == null){
            try{
                config = Configuration.loadConfig();
            } catch (RuntimeException e){
                System.out.println("Error while loading configuration");
                e.printStackTrace();
            }
        }
        return config;
    }

    public static synchronized TicketPool getTicketPool(){
        if(ticketPool == null){
            ticketPool = new TicketPool();
        }
        return ticketPool;
    }
}
