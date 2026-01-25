import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int maxTicketCapacity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private String eventName;
    private BigDecimal ticketPrice;
    private int maxQuantity;

    // Method to handle user input for all configurations
    public static Configuration getConfigurationFromUser() {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();
        // Input validation with clear error messages
        config.setTotalTickets(getValidInput("Enter the total number of tickets: ", "Please enter a valid number of tickets."));
        config.setMaxTicketCapacity(getValidInput("Enter the max capacity of the ticket pool: ", "Please enter a valid ticket pool capacity."));
        config.setTicketReleaseRate(getValidInput("Enter the ticket release rate (tickets per second): ", "Please enter a valid ticket release rate."));
        config.setCustomerRetrievalRate(getValidInput("Enter the customer retrieval rate (tickets per second): ", "Please enter a valid customer retrieval rate."));
        config.setMaxQuantity(getValidInput("Enter the maximum No of tickets one customer can buy: ", "Please enter a valid quantity."));

        try {
            System.out.print("Enter event name: ");
            config.setEventName(scanner.nextLine());
            System.out.print("Enter ticket price: ");
            config.setTicketPrice(scanner.nextBigDecimal());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
        return config;
    }

    // Utility method to get valid input
    private static int getValidInput(String prompt, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input <= 0) {
                    System.out.println("Input cannot be negative or zero. " + errorMessage);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. " + errorMessage);
            }
        }
        return input;
    }

    // Getters and Setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
