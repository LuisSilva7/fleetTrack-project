package org.fleettrack.maintenance;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fleettrack.vehicle.Vehicle;
import org.fleettrack.vehicle.VehicleMapper;
import org.fleettrack.vehicle.VehicleResponse;

@ApplicationScoped
public class MaintenanceMapper {

    @Inject
    VehicleMapper vehicleMapper;

    public Maintenance toMaintenanceEntity(MaintenanceRequest request, Vehicle vehicle) {
        return Maintenance.builder()
                .maintenanceType(request.maintenanceType())
                .date(request.date())
                .cost(request.cost())
                .description(request.description())
                .vehicle(vehicle)
                .build();
    }

    public MaintenanceResponse toMaintenanceResponse(Maintenance maintenance) {
        VehicleResponse vehicleResponse = vehicleMapper.toVehicleResponse(maintenance.getVehicle());

        return new MaintenanceResponse(
                maintenance.getId(),
                maintenance.getMaintenanceType(),
                maintenance.getDate(),
                maintenance.getCost(),
                maintenance.getDescription(),
                vehicleResponse
        );
    }
}
