package com.ticketing.backend.controller;

import com.ticketing.backend.services.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @GetMapping
    public ResponseEntity<List<String>> getLogs() {
        return ResponseEntity.ok(LogService.getLogs());
    }
}

