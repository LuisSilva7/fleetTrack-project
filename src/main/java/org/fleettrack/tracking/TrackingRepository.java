package org.fleettrack.tracking;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TrackingRepository implements PanacheRepository<Tracking> {
}
