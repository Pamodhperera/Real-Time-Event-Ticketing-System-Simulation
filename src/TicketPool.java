import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketQueue;
    private int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.ticketQueue = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    // Method to add tickets (Vendors adding tickets)
    public synchronized void addTicket(Ticket ticket) {
        while(ticketQueue.size() >= maxCapacity) {
            try {
                System.out.println("Ticket pool is full. Cannot add more tickets.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        ticketQueue.add(ticket);
        System.out.println(Thread.currentThread().getName()+" added a ticket. Total tickets: " + ticketQueue.size());
        notifyAll();
    }

    // Method to remove tickets (Customers purchasing tickets)
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()){
            try {
                System.out.println("No tickets available for customers.");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll();
        System.out.println(Thread.currentThread().getName() + " Bought a ticket. Ticket info: " +  ticket);
        System.out.println("Remaining tickets: " + ticketQueue.size());
        notifyAll();
        return ticket;
    }

}
