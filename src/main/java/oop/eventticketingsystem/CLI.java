package oop.eventticketingsystem;

import oop.eventticketingsystem.Configurations.Configuration;

import java.util.Scanner;

public class CLI {

    public static Scanner scanner;

    public static void setConfiguration(){
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
        setConfiguration();
    }
}
