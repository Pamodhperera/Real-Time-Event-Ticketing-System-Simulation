package com.ticketing.backend.model;

import com.ticketing.backend.services.LogService;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final Configuration config;

    public Vendor(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.config = config;
    }

    @Override
    public void run() {
        for (int i = 0; i < config.getTotalTickets(); i++) {
            String ticket = "Ticket " + i;
            ticketPool.addTicket(ticket);
            LogService.log("Vendor added: " + ticket);
            try {
                Thread.sleep(1000 / config.getTicketReleaseRate());
            } catch (InterruptedException ignored) {}
        }
    }
}