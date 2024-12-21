package org.fleettrack.route;

import org.fleettrack.vehicle.VehicleResponse;

import java.util.List;

public record RouteResponse(
        Long id,
        String originStreet,
        String originCity,
        String destinationStreet,
        String destinationCity,
        double distance,
        String estimatedTime,
        boolean tolls,
        List<VehicleResponse> vehicles
) {
}
