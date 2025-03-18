# Delivery Optimizer

## Overview
This service calculates the optimal delivery route for a delivery executive who has been assigned multiple orders (for now, two orders). It factors in:
- Preparation times at each restaurant (pt1, pt2)
- Distance between locations (calculated using the Haversine formula)
- Average speed of 20 km/h to estimate travel times

The goal is to minimize total delivery time.

## Approach
- The application considers two possible delivery sequences (R1 -> R2 -> C1 -> C2 OR R2 -> R1 -> C2 -> C1).
- Calculates estimated travel + waiting times for each sequence.
- Returns the one with the lesser total time.
- Designed to be extensible for N orders in the future.

## Assumptions
- Aman, restaurants (R1 and R2), and order details are known at the same time.
- Preparation starts immediately with no delays.
- Average delivery speed is assumed to be constant (20 km/h).
- Actual road network is simplified with haversine distance.

## Tech Stack
- Java 17
- Spring Boot
- JUnit 5

## How to Run

### Run Locally:
```bash
./mvnw spring-boot:run
