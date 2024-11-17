package oop.eventticketingsystem.Configurations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class Configuration implements Serializable {

    private int numberOfTickets;
    // number tickets released at once
    private int ticketReleaseRate;
    // number of tickets sold at once
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private static final String CONFIG_FILE = "src/main/java/oop/eventticketingsystem/Configurations/config.json";
    private static final GsonBuilder builder = new GsonBuilder();
    private static Gson gson;

    public Configuration(int numOfTickets, int releaseRate, int retrievalRate, int ticketCapacity) {
        numberOfTickets = numOfTickets;
        ticketReleaseRate = releaseRate;
        customerRetrievalRate = retrievalRate;
        maxTicketCapacity = ticketCapacity;
    }

    public static void saveConfig(Configuration config){
        try(Writer writer = new FileWriter(CONFIG_FILE)){
            builder.setPrettyPrinting();
            gson = builder.create();
            gson.toJson(config, writer);

        } catch (IOException e){
            System.out.println("Error while saving configuration");
            throw new RuntimeException(e);
        }

    }

    public static Configuration loadConfig(){
        try(Reader reader = new FileReader(CONFIG_FILE)){
            Configuration config;
            gson = new Gson();
            config = gson.fromJson(reader, Configuration.class);
            return config;
        } catch (IOException e){
            System.out.println("Error while loading configuration");
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
