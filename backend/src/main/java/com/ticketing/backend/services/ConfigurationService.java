package com.ticketing.backend.services;

import lombok.Getter;
import org.springframework.stereotype.Service;

import com.ticketing.backend.model.Configuration;

@Service
public class ConfigurationService {

    @Getter
    private static Configuration configuration;

    public static Configuration saveConfiguration(Configuration config) {
        configuration = config;
        return config;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        ConfigurationService.configuration = configuration;
    }
}