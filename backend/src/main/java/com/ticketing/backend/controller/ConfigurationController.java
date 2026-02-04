package com.ticketing.backend.controller;

import com.ticketing.backend.model.Configuration;
import com.ticketing.backend.services.ConfigurationService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/configuration")
@CrossOrigin("http://localhost:4200/")
public class ConfigurationController {

    @PostMapping
    public Configuration setConfiguration(@RequestBody Configuration configuration) {
        return ConfigurationService.saveConfiguration(configuration);
//        return ResponseEntity.ok("Configuration saved successfully.");
    }
}