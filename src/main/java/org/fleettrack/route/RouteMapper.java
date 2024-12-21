package org.fleettrack.route;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fleettrack.vehicle.Vehicle;
import org.fleettrack.vehicle.VehicleMapper;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RouteMapper {

    @Inject
    VehicleMapper vehicleMapper;

    public Route toRouteEntity(RouteRequest request, Vehicle vehicle) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);

        return Route.builder()
                .originStreet(request.originStreet())
                .originCity(request.originCity())
                .destinationStreet(request.destinationStreet())
                .destinationCity(request.destinationCity())
                .distance(request.distance())
                .estimatedTime(request.estimatedTime())
                .tolls(request.tolls())
                .vehicles(vehicles)
                .build();
    }

    public RouteResponse toRouteResponse(Route route) {
        return new RouteResponse(
                route.getId(),
                route.getOriginStreet(),
                route.getOriginCity(),
                route.getDestinationStreet(),
                route.getDestinationCity(),
                route.getDistance(),
                route.getEstimatedTime(),
                route.isTolls(),
                route.getVehicles().stream().map(vehicleMapper::toVehicleResponse).toList()
        );
    }
}
