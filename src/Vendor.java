import java.math.BigDecimal;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;
    private int ticketId;
    private String eventName;
    private BigDecimal ticketPrice;


    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate, int ticketId, String eventName, BigDecimal ticketPrice) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public void run() {
        for (int i = 1; i <= ticketReleaseRate; i++) {
            Ticket ticket = new Ticket( ticketId + "" + i, eventName, ticketPrice);
            ticketPool.addTicket(ticket);

            try {
                Thread.sleep(1000 / ticketReleaseRate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
