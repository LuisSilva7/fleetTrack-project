package org.fleettrack.maintenance;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaintenanceRepository implements PanacheRepository<Maintenance> {
}
