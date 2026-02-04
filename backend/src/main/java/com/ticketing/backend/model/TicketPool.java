package com.ticketing.backend.model;

import java.util.*;

public class TicketPool {
    private final Queue<String> ticketQueue = new LinkedList<>();
    private  int maxCapacity;


    public synchronized void initializePool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.ticketQueue.clear();
    }

    public synchronized void addTicket(String ticket) {
        while (ticketQueue.size() >= maxCapacity) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        ticketQueue.add(ticket);
        notifyAll();
    }

    public synchronized String removeTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        String ticket = ticketQueue.poll();
        notifyAll();
        return ticket;
    }
}