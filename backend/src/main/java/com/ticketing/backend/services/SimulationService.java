package com.ticketing.backend.services;

import com.ticketing.backend.model.Customer;
import com.ticketing.backend.model.TicketPool;
import com.ticketing.backend.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.backend.model.Configuration;
import java.util.*;
import java.util.concurrent.*;

@Service
public class SimulationService {

    @Autowired
    private Executor taskExecutor;

    private List<Future<?>> runningTasks = new ArrayList<>();
    private final TicketPool ticketPool = new TicketPool();

    public void startSimulation() {
        Configuration config = ConfigurationService.getConfiguration();
        if (config == null) {
            throw new IllegalStateException("Configuration not set.");
        }

        for (int i = 0; i < config.getTotalTickets(); i++) {
            Runnable vendorTask = new Vendor(ticketPool, config);
            Future<?> vendorFuture = ((ExecutorService) taskExecutor).submit(vendorTask);
            runningTasks.add(vendorFuture);
        }

        for (int i = 0; i < config.getTotalTickets(); i++) {
            Runnable customerTask = new Customer(ticketPool, config);
            Future<?> customerFuture = ((ExecutorService) taskExecutor).submit(customerTask);
            runningTasks.add(customerFuture);
        }
    }

    public void stopSimulation() {
        for (Future<?> task : runningTasks) {
            task.cancel(true);
        }
        runningTasks.clear();
    }
}
