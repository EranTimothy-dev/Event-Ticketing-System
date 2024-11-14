package oop.eventticketingsystem;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class TicketPool implements TicketHandling{


    List<Integer> tickets = synchronizedList(new ArrayList<Integer>());


    @Override
    public void addTickets() {

    }

    @Override
    public void removeTickets() {

    }
}
