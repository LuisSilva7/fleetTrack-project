package org.fleettrack.tracking;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class TrackingRepository implements PanacheRepository<Tracking> {

    public Long save(Tracking tracking) {
        persist(tracking);
        return tracking.getId();
    }

    public Optional<Tracking> findLastTrackingByVehicleId(Long vehicleId) {
        return getEntityManager().createQuery(
                        "SELECT t FROM Tracking t WHERE t.vehicle.id = :vehicleId ORDER BY t.timestamp DESC",
                        Tracking.class
                )
                .setParameter("vehicleId", vehicleId)
                .setMaxResults(1)
                .getResultStream()
                .findFirst();
    }
}
