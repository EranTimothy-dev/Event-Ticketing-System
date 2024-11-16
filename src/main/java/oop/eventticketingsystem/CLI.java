package oop.eventticketingsystem;

import oop.eventticketingsystem.Configurations.Configuration;
import oop.eventticketingsystem.Users.Customer;
import oop.eventticketingsystem.Users.Vendor;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CLI {

    public static Scanner scanner;
    public static int totalTickets;
    public static int maximumTicketCapacity;
    public static int ticketsReleaseRate;
    public static int customerRetrievalRate;

    public static void getConfiguration() throws InterruptedException {
        while(true){
            while(true){
                scanner = new Scanner(System.in);
                System.out.print("Total number of Tickets: ");
                try{
                    totalTickets = scanner.nextInt();
                    if(totalTickets <= 0){
                        System.out.println("Invalid Input! Please enter a positive integer and try again.");
                        continue;
                    }

                    break;
                } catch(Exception e){
                    System.out.println("Invalid Input! Please enter a valid value and try again.");
                }

            }
            while (true){
                scanner = new Scanner(System.in);
                System.out.print("Maximum Ticket Capacity: ");
                try{
                    maximumTicketCapacity = scanner.nextInt();
                    if (maximumTicketCapacity > totalTickets){
                        System.out.println("Max Capacity cannot be greater than Total Tickets");
                        continue;
                    }
                    if (maximumTicketCapacity <= 0 ){
                        System.out.println("Invalid Input! Ticket Capacity should be greater than 0. Please try again.");
                        continue;
                    }
                    break;
                } catch (Exception e){
                    System.out.println("Invalid Input! Please enter a valid integer less than Total Tickets.");
                }
            }
            while (true){
                scanner = new Scanner(System.in);
                System.out.print("Tickets Release Rate: ");
                try{
                    ticketsReleaseRate = scanner.nextInt();
                    if (ticketsReleaseRate > maximumTicketCapacity){
                        System.out.println("Release Rate of Tickets cannot be greater than the Maximum Ticket Capacity. Please try again.");
                        continue;
                    }
                    if (ticketsReleaseRate <= 0 ){
                        System.out.println("Invalid Input! Tickets Release Rate should be greater than 0. Please try again.");
                        continue;
                    }
                    break;
                } catch (Exception e){
                    System.out.println("Invalid Input! Please enter a valid integer value.");
                }
            }
            while(true){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Customer Retrieval Rate: ");
                try{
                    customerRetrievalRate = scanner.nextInt();
                    if (customerRetrievalRate >= maximumTicketCapacity){
                        System.out.println("Retrieval Rate cannot be greater than the Maximum Ticket Capacity. Please try again.");
                        continue;
                    }
                    if (customerRetrievalRate <= 0 ){
                        System.out.println("Invalid Input! Retrieval Rate should be greater than 0. Please try again.");
                        continue;
                    }
                    break;
                } catch(Exception e){
                    System.out.println("Invalid Input! Please enter a valid integer value.");
                }
            }
            break;
        }

        Configuration systemConfigurations = new Configuration(totalTickets, ticketsReleaseRate, customerRetrievalRate, maximumTicketCapacity);
        Configuration.saveConfig(systemConfigurations);
        Model.setConfiguration(systemConfigurations);
        System.out.println("System configurations saved. Starting system...");


        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> {
            ExecutorService vendorService = null;
            try {
                vendorService = Executors.newFixedThreadPool(ticketsReleaseRate);
                vendorService.execute(new Vendor());
            } finally {
                Thread.currentThread().interrupt();
                vendorService.shutdown();

            }
        },0,1, TimeUnit.SECONDS);

        service.scheduleAtFixedRate(() -> {
            ExecutorService customerService = null;
            try {
                customerService = Executors.newFixedThreadPool(systemConfigurations.getCustomerRetrievalRate());
                customerService.execute(new Customer());
            } finally {
                Thread.currentThread().interrupt();
                customerService.shutdown();
            }
        },0,1, TimeUnit.SECONDS);


    }


    public static void main(String[] args) throws InterruptedException {
        getConfiguration();
    }
}
