package com.lucidity.deliveryoptimizer.service;

import com.lucidity.deliveryoptimizer.model.Location;
import com.lucidity.deliveryoptimizer.model.Order;
import com.lucidity.deliveryoptimizer.util.HaversineUtil;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class RoutingService {

    private static final Logger logger = LoggerFactory.getLogger(RoutingService.class);
    private static final double AVERAGE_SPEED_KMH = 20.0;

    public String calculateOptimalRoute(Location start, List<Order> orders) {
        logger.info("Calculating optimal route for {} orders", orders.size());
        if (orders.size() != 2) {
            logger.warn("Currently supports only 2 orders, received: {}", orders.size());
            throw new IllegalArgumentException("Currently only supports 2 orders");
        }

        Order o1 = orders.get(0);
        Order o2 = orders.get(1);

        double timeOption1 = estimateTotalTime(start, o1, o2);
        double timeOption2 = estimateTotalTime(start, o2, o1);

        String selectedRoute = timeOption1 <= timeOption2 ? "Route 1 (R1 -> R2 -> C1 -> C2)" : "Route 2 (R2 -> R1 -> C2 -> C1)";
        logger.info("Best route selected: {}", selectedRoute);
        return selectedRoute;
    }

    private double estimateTotalTime(Location start, Order first, Order second) {
        logger.debug("Estimating time for sequence: {} -> {}", first.getRestaurant(), second.getRestaurant());

        return estimateTime(start, first.getRestaurant().getLocation(), first.getRestaurant().getPrepTimeMinutes()) + estimateTime(first.getRestaurant().getLocation(), second.getRestaurant().getLocation(), second.getRestaurant().getPrepTimeMinutes()) + estimateTime(second.getRestaurant().getLocation(), first.getConsumer().getLocation(), 0) + estimateTime(first.getConsumer().getLocation(), second.getConsumer().getLocation(), 0);
    }

    private double estimateTime(Location from, Location to, int prepTime) {
        double distanceKm = HaversineUtil.distance(from, to);
        double travelTime = (distanceKm / AVERAGE_SPEED_KMH) * 60; // convert hr to min
        return Math.max(prepTime, travelTime);
    }
}