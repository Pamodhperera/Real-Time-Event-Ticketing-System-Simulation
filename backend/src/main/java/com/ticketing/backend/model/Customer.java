package com.ticketing.backend.model;

import com.ticketing.backend.services.LogService;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final Configuration config;

    public Customer(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.config = config;
    }

    @Override
    public void run() {
        for (int i = 0; i < config.getCustomerRetrievalRate(); i++) {
            String ticket = ticketPool.removeTicket();
            LogService.log("Customer bought: " + ticket);
            try {
                Thread.sleep(1000 / config.getCustomerRetrievalRate());
            } catch (InterruptedException ignored) {}
        }
    }
}