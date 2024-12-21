package org.fleettrack.route;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RouteRepository implements PanacheRepository<Route> {

    public Long saveRoute(Route route) {
        persist(route);
        return route.getId();
    }
}
