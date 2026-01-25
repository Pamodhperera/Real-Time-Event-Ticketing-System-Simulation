import java.util.Scanner;

public class TicketingSystemCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Welcome to ticketing system!\n");

        // Get configuration from saved file
        Configuration config = Configuration.getConfigurationFromUser();
        // Initialize the ticket pool
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

        Vendor[] vendors = new Vendor[config.getTotalTickets() / config.getTicketReleaseRate()];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate(), i, config.getEventName(), config.getTicketPrice());
            Thread vendorThread = new Thread(vendors[i], "Vendor " + (i + 1));
            vendorThread.start();
        }

        Customer[] customers = new Customer[config.getTotalTickets() / config.getMaxQuantity()];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, config.getCustomerRetrievalRate(), config.getMaxQuantity());
            Thread customerThread = new Thread(customers[i], "Customer " + (i + 1));
            customerThread.start();
        }

    }
}
