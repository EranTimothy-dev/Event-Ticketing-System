package oop.eventticketingsystem;

import oop.eventticketingsystem.Configurations.Configuration;
import oop.eventticketingsystem.Tickets.Ticket;
import oop.eventticketingsystem.Tickets.TicketPool;
import oop.eventticketingsystem.Users.Customer;
import oop.eventticketingsystem.Users.Vendor;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CLI {

    public static Scanner scanner;

    public static void setConfiguration() throws InterruptedException {
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
        Model.setConfiguration(systemConfigurations);

//        Configuration loaded_configs = Configuration.loadConfig();
//        System.out.println(loaded_configs.getNumberOfTickets());
//        System.out.println(loaded_configs.getMaxTicketCapacity());
//        System.out.println(loaded_configs.getCustomerRetrievalRate());
//        System.out.println(loaded_configs.getTicketReleaseRate());




        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.scheduleAtFixedRate(() -> {
            ExecutorService vendorService = null;
            try {
                vendorService = Executors.newFixedThreadPool(ticketsReleaseRate);
                vendorService.execute(new Vendor());
            } finally {
                Thread.currentThread().interrupt();
                vendorService.shutdown();

            }
//            System.out.println("\nVendor thread terminated");
        },0,1, TimeUnit.SECONDS);

        service.scheduleAtFixedRate(() -> {
            ExecutorService customerService = null;
            try {
                customerService = Executors.newFixedThreadPool(customerRetrievalRate);
                customerService.execute(new Customer());
            } finally {
                Thread.currentThread().interrupt();
                customerService.shutdown();
            }
//            System.out.println("\nCustomer Thread completed");
        },0,1, TimeUnit.SECONDS);

    }


    public static void main(String[] args) throws InterruptedException {
        setConfiguration();
    }
}
