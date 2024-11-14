package oop.eventticketingsystem;

import com.google.gson.Gson;
import oop.eventticketingsystem.Configurations.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EventTicketingSystemApplication {

    public static Scanner scanner;

    public static void getConfiguration(){
        scanner = new Scanner(System.in);
        System.out.print("Maximum Ticket Capacity: ");
        int maximumTicketCapacity = scanner.nextInt();
        System.out.print("Total number of Tickets: ");
        int totalTickets = scanner.nextInt();
        System.out.print("Tickets Release Rate: ");
        int ticketsReleaseRate = scanner.nextInt();
        System.out.print("Customer Retrieval Rate: ");
        int customerRetrievalRate = scanner.nextInt();

        Configuration systemConfigurations = new Configuration(totalTickets, ticketsReleaseRate, customerRetrievalRate, maximumTicketCapacity);
        Configuration.saveConfig(systemConfigurations);

//        Configuration loaded_configs = Configuration.loadConfig();
//        System.out.println(loaded_configs.getNumberOfTickets());
//        System.out.println(loaded_configs.getMaxTicketCapacity());
//        System.out.println(loaded_configs.getCustomerRetrievalRate());
//        System.out.println(loaded_configs.getTicketReleaseRate());


    }

    public static void main(String[] args) {
//        SpringApplication.run(EventTicketingSystemApplication.class, args);
        getConfiguration();

    }

}
