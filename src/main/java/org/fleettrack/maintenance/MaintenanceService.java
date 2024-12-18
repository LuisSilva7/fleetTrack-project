package org.fleettrack.maintenance;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MaintenanceService {

    @Inject
    MaintenanceRepository maintenanceRepository;
    @Inject
    MaintenanceMapper maintenanceMapper;
}
