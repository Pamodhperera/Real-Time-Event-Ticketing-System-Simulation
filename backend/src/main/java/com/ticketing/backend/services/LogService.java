package com.ticketing.backend.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogService {

    private static final List<String> logs = Collections.synchronizedList(new ArrayList<>());

    public static void log(String message) {
        logs.add(message);
    }

    public static List<String> getLogs() {
        return new ArrayList<>(logs);
    }
}

