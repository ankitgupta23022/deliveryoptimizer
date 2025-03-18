package com.lucidity.deliveryoptimizer.util;

import com.lucidity.deliveryoptimizer.model.Location;

public class HaversineUtil {

    private static final int EARTH_RADIUS_KM = 6371;

    public static double distance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.getLatitude());
        double lon1 = Math.toRadians(loc1.getLongitude());
        double lat2 = Math.toRadians(loc2.getLatitude());
        double lon2 = Math.toRadians(loc2.getLongitude());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

        return 2 * EARTH_RADIUS_KM * Math.asin(Math.sqrt(a));
    }
}
