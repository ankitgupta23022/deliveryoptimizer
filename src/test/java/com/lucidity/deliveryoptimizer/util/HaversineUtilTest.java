package com.lucidity.deliveryoptimizer.util;

import com.lucidity.deliveryoptimizer.model.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HaversineUtilTest {

    @Test
    void shouldCalculateNonZeroDistance() {
        Location loc1 = new Location(12.9352, 77.6245); // Koramangala
        Location loc2 = new Location(12.9402, 77.6315); // Nearby location

        double distance = HaversineUtil.distance(loc1, loc2);
        assertTrue(distance > 0);
        assertTrue(distance < 10); // should be a few kms max
    }

    @Test
    void shouldReturnZeroForSameLocation() {
        Location loc1 = new Location(12.9352, 77.6245);
        double distance = HaversineUtil.distance(loc1, loc1);
        assertEquals(0, distance, 0.0001);
    }
}
