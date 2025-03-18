package com.lucidity.deliveryoptimizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private Location location;
    private int prepTimeMinutes;
}
