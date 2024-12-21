package org.fleettrack.route;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fleettrack.common.PageResponse;
import org.fleettrack.exception.custom.ResourceNotFoundException;
import org.fleettrack.vehicle.*;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RouteService {

    @Inject
    RouteRepository routeRepository;
    @Inject
    RouteMapper routeMapper;
    @Inject
    VehicleRepository vehicleRepository;

    public Long createRoute(RouteRequest request) {
        Optional<Vehicle> vehicle = vehicleRepository.findByIdOptional(request.vehicleId());

        Route route = routeMapper.toRouteEntity(request, vehicle.orElse(null));

        return routeRepository.saveRoute(route);
    }

    public PageResponse<RouteResponse> findAllRoutes(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            pageNumber = 0;
        }

        if (pageSize <= 0) {
            pageSize = 10;
        }

        List<Route> routes = routeRepository.findAll()
                .page(Page.of(pageNumber, pageSize))
                .list();

        long totalRoutes = routeRepository.count();
        int totalPages = (int) Math.ceil((double) totalRoutes / pageSize);

        return new PageResponse<>(
                routes.stream().map(routeMapper::toRouteResponse).toList(),
                pageNumber,
                pageSize,
                totalRoutes,
                totalPages,
                pageNumber == 0,
                pageNumber == totalPages - 1
        );
    }

    public RouteResponse findRouteById(Long routeId) {
        Route route = routeRepository.findByIdOptional(routeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Route with ID: " + routeId + " does not exist!"));

        return routeMapper.toRouteResponse(route);
    }

    public void updateRouteVehicle(Long routeId, RouteVehicleRequest request) {
        Route route = routeRepository.findByIdOptional(routeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Route with ID: " + routeId + " does not exist!"));

        Optional<Vehicle> vehicle = vehicleRepository.findByIdOptional(request.vehicleId());
        vehicle.ifPresent(route::addVehicle);
        routeRepository.persist(route);
    }
}
