package org.fleettrack.maintenance;

import org.fleettrack.vehicle.VehicleResponse;

public record MaintenanceResponse (
        Long id,
        String maintenanceType,
        String date,
        double cost,
        String description,
        VehicleResponse vehicle
){
}
