package oop.eventticketingsystem;

import java.io.*;

public class Configuration implements Serializable {

    private int numberOfTickets;
    // number tickets released at once
    private int ticketReleaseRate;
    // number of tickets sold at once
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private static final String CONFIG_FILE = "config.txt";

    public Configuration(int numOfTickets, int releaseRate, int retrievalRate, int ticketCapacity) {
        numberOfTickets = numOfTickets;
        ticketReleaseRate = releaseRate;
        customerRetrievalRate = retrievalRate;
        maxTicketCapacity = ticketCapacity;
    }

    public static void saveConfig(Configuration config){
        try{
            FileOutputStream file = new FileOutputStream(CONFIG_FILE);
            ObjectOutputStream serialize = new ObjectOutputStream(file);

            serialize.writeObject(config);
            serialize.close();
            file.close();

        } catch (IOException e){
            System.out.println("Error while saving configuration");
            throw new RuntimeException(e);
        }

    }

    public static Configuration loadConfig(){
        try{
            FileInputStream file = new FileInputStream(CONFIG_FILE);
            ObjectInputStream deserialize = new ObjectInputStream(file);

            Configuration configDetails = (Configuration) deserialize.readObject();

            deserialize.close();
            file.close();
            return configDetails;

        } catch (IOException e){
            System.out.println("Error while loading configuration");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find class in config file");
            throw new RuntimeException(e);
        }
    }


    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }


    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }




}
