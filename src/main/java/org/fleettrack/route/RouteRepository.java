package org.fleettrack.route;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RouteRepository implements PanacheRepository<Route> {

    public Long saveRoute(Route route) {
        persist(route);
        return route.getId();
    }

    public List<Route> findRoutesByVehicleId(Long vehicleId) {
        return getEntityManager()
                .createQuery("SELECT r FROM Route r JOIN r.vehicles v WHERE v.id = :vehicleId", Route.class)
                .setParameter("vehicleId", vehicleId)
                .getResultList();
    }
}
