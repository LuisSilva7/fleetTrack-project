package org.fleettrack.vehicle;

public record VehicleResponse(
        Long id,
        String plate,
        String model,
        int manufactureYear,
        int currentKm,
        int capacity,
        String acquisitionDate,
        String status
) {
}
