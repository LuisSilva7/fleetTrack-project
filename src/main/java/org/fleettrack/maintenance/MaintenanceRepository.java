package org.fleettrack.maintenance;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaintenanceRepository implements PanacheRepository<Maintenance> {

    public Long saveMaintenance(Maintenance maintenance) {
        persist(maintenance);
        return maintenance.getId();
    }

    public double sumMaintenanceCostsByVehicleId(Long vehicleId) {
        return find("vehicle.id", vehicleId).stream()
                .mapToDouble(Maintenance::getCost)
                .sum();
    }
}
