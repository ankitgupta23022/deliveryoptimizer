package com.lucidity.deliveryoptimizer.service;

import com.lucidity.deliveryoptimizer.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoutingServiceTest {

    private RoutingService routingService;
    private Location amanLocation;
    private Restaurant r1, r2;
    private Consumer c1, c2;
    private Order order1, order2;

    @BeforeEach
    void setUp() {
        routingService = new RoutingService();
        amanLocation = new Location(12.9352, 77.6245);

        r1 = new Restaurant(new Location(12.9360, 77.6228), 10);
        r2 = new Restaurant(new Location(12.9337, 77.6300), 8);
        c1 = new Consumer(new Location(12.9381, 77.6270));
        c2 = new Consumer(new Location(12.9402, 77.6315));

        order1 = new Order(r1, c1);
        order2 = new Order(r2, c2);
    }

    @Test
    void shouldReturnBestRouteForTwoOrders() {
        List<Order> orders = Arrays.asList(order1, order2);
        String result = routingService.calculateOptimalRoute(amanLocation, orders);
        assertTrue(result.contains("Route"));
    }

    @Test
    void shouldThrowExceptionWhenMoreThanTwoOrders() {
        List<Order> orders = Arrays.asList(order1, order2, order1); // 3 orders
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            routingService.calculateOptimalRoute(amanLocation, orders);
        });
        assertEquals("Currently only supports 2 orders", ex.getMessage());
    }
}