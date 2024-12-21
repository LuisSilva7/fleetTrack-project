package org.fleettrack.cost;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fleettrack.maintenance.MaintenanceRepository;
import org.fleettrack.route.Route;
import org.fleettrack.route.RouteRepository;
import org.fleettrack.vehicle.VehicleResponse;
import org.fleettrack.vehicle.VehicleService;

@ApplicationScoped
public class CostService {

    @Inject
    VehicleService vehicleService;
    @Inject
    MaintenanceRepository maintenanceRepository;
    @Inject
    RouteRepository routeRepository;

    public double findVehicleCostById(Long vehicleId) {
        VehicleResponse vehicleResponse = vehicleService.findVehicleById(vehicleId);

        // Simulated values
        double fuelCostPerLiter = 1.5;
        double consumptionRate = 12.0;
        double tollCostPerRoute = 5.0;

        // 1. Calculate Fuel Cost
        double fuelCost = (vehicleResponse.currentKm() / consumptionRate) * fuelCostPerLiter;

        // 2. Calculate Maintenance Cost
        double maintenanceCost = maintenanceRepository.sumMaintenanceCostsByVehicleId(vehicleId);

        // 3. Calculate Toll Costs
        double tollCosts = routeRepository.findRoutesByVehicleId(vehicleId).stream()
                .filter(Route::isTolls)
                .count() * tollCostPerRoute;

        // 4. Total Cost
        return fuelCost + maintenanceCost + tollCosts;
    }
}
