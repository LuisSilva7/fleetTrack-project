package org.fleettrack.maintenance;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface MaintenanceRepository extends PanacheRepository<Maintenance> {
}
