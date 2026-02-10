package com.mednex.hms.analytics;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @GetMapping("/bed-occupancy")
    public Map<String, Integer> bedOccupancy() {
        return Map.of(
            "totalBeds", 150,
            "occupiedBeds", 96,
            "availableBeds", 54
        );
    }
}
