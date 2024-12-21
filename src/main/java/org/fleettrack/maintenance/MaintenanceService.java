package org.fleettrack.maintenance;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fleettrack.common.PageResponse;
import org.fleettrack.exception.custom.ResourceNotFoundException;
import org.fleettrack.exception.custom.VehicleNotActiveException;
import org.fleettrack.vehicle.Vehicle;
import org.fleettrack.vehicle.VehicleRepository;

import java.util.List;

import static org.fleettrack.vehicle.VehicleStatus.ACTIVE;
import static org.fleettrack.vehicle.VehicleStatus.INACTIVE;

@ApplicationScoped
public class MaintenanceService {

    @Inject
    MaintenanceRepository maintenanceRepository;
    @Inject
    MaintenanceMapper maintenanceMapper;
    @Inject
    VehicleRepository vehicleRepository;

    public Long createMaintenance(MaintenanceRequest request) {
        Vehicle vehicle = vehicleRepository.findByIdOptional(request.vehicleId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle with ID: " + request.vehicleId() + " does not exist!"));

        if(vehicle.getStatus() != ACTIVE) {
            throw new VehicleNotActiveException("Vehicle must be active!");
        }

        vehicle.setStatus(INACTIVE);
        vehicleRepository.persist(vehicle);

        return maintenanceRepository.saveMaintenance(
                maintenanceMapper.toMaintenanceEntity(request, vehicle)
        );
    }

    public PageResponse<MaintenanceResponse> findAllMaintenances(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            pageNumber = 0;
        }

        if (pageSize <= 0) {
            pageSize = 10;
        }

        List<Maintenance> maintenances = maintenanceRepository.findAll()
                .page(Page.of(pageNumber, pageSize))
                .list();

        long totalMaintenances = maintenanceRepository.count();
        int totalPages = (int) Math.ceil((double) totalMaintenances / pageSize);

        return new PageResponse<>(
                maintenances.stream().map(maintenanceMapper::toMaintenanceResponse).toList(),
                pageNumber,
                pageSize,
                totalMaintenances,
                totalPages,
                pageNumber == 0,
                pageNumber == totalPages - 1
        );
    }

    public MaintenanceResponse findMaintenanceById(Long maintenanceId) {
        Maintenance maintenance = maintenanceRepository.findByIdOptional(maintenanceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Driver with ID: " + maintenanceId + " does not exist!"));

        return maintenanceMapper.toMaintenanceResponse(maintenance);
    }
}
