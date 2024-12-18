package org.fleettrack.route;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RouteService {

    @Inject
    RouteRepository routeRepository;
    @Inject
    RouteMapper routeMapper;
}
