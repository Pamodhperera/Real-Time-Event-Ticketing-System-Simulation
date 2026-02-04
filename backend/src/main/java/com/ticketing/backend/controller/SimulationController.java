package com.ticketing.backend.controller;

import com.ticketing.backend.services.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulation")
@CrossOrigin("http://localhost:4200")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @PostMapping("/start")
    public ResponseEntity<String> startSimulation() {
        simulationService.startSimulation();
        return ResponseEntity.ok("Simulation started.");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        simulationService.stopSimulation();
        return ResponseEntity.ok("Simulation stopped.");
    }
}
