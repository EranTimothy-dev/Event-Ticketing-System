package oop.eventticketingsystem.model.tickets;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticId;

    public Ticket(){}

//    public Ticket(int ticketId){
//        this.ticId = ticketId;
//    }


}
