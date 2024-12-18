package org.fleettrack.tracking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TrackingService {

    @Inject
    TrackingRepository trackingRepository;
    @Inject
    TrackingMapper trackingMapper;
}
