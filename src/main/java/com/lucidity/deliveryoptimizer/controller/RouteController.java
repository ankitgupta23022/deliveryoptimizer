package com.lucidity.deliveryoptimizer.controller;

import com.lucidity.deliveryoptimizer.model.*;
import com.lucidity.deliveryoptimizer.service.RoutingService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    private final RoutingService routingService;

    public RouteController(RoutingService routingService) {
        this.routingService = routingService;
    }

    @GetMapping("/best")
    public String getBestRoute() {
        // Sample data for testing
        Location amanLocation = new Location(12.9352, 77.6245); // Koramangala

        Restaurant r1 = new Restaurant(new Location(12.9360, 77.6228), 10);
        Restaurant r2 = new Restaurant(new Location(12.9337, 77.6300), 8);

        Consumer c1 = new Consumer(new Location(12.9381, 77.6270));
        Consumer c2 = new Consumer(new Location(12.9402, 77.6315));

        Order o1 = new Order(r1, c1);
        Order o2 = new Order(r2, c2);

        return routingService.calculateOptimalRoute(amanLocation, Arrays.asList(o1, o2));
    }
}
